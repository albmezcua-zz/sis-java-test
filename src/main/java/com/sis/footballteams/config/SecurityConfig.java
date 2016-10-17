package com.sis.footballteams.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sis.footballteams.security.AuthenticationInvalidResponse;
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

@Configuration
@EnableWebSecurity
@ComponentScan("com.sis.footballteams.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  IdentityAuthenticationProvider identityAuthenticationProvider;
    @Autowired
    private IdentityPreAuthenticatedProcessingFilter identityPreAuthenticationFilter;

    @Autowired
    AuthenticationInvalidResponse invalidResponse;


    public SecurityConfig() {
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(invalidResponse)
                .and()
                .addFilter(this.identityPreAuthenticationFilter)
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").access("isAuthenticated()");
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.identityAuthenticationProvider);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}