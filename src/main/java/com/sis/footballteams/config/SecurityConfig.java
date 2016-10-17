package com.sis.footballteams.config;


import com.sis.footballteams.security.IdentityAuthenticationProvider;
import com.sis.footballteams.security.IdentityPreAuthenticatedProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

@Configuration
@EnableWebSecurity
@ComponentScan("com.sis.footballteams.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  IdentityAuthenticationProvider identityAuthenticationProvider;
    @Autowired
    private IdentityPreAuthenticatedProcessingFilter identityPreAuthenticationFilter;

    public SecurityConfig() {
    }

    protected void configure(HttpSecurity http) throws Exception {
        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)(
                (HttpSecurity)http.addFilter(this.identityPreAuthenticationFilter)
                        .csrf().disable())
                .authorizeRequests().antMatchers(new String[]{"/**"})).access("isAuthenticated()");
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.identityAuthenticationProvider);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}