package com.sis.footballteams.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by lopeza on 15/10/2016.
 */
@Component
public class IdentityPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
    @Autowired
    private AuthenticationManager authenticationManager;

    public IdentityPreAuthenticatedProcessingFilter() {
        this.setAuthenticationDetailsSource(new IdentityAuthenticationHeaderDetailsSource());
    }

    @PostConstruct
    public void postConstruct() {
        this.setAuthenticationManager(this.authenticationManager);
    }

    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
        return "Anonymous";
    }

    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        return "N/A";
    }
}