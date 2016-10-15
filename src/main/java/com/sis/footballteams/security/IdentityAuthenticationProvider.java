package com.sis.footballteams.security;

import com.sis.footballteams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * Created by lopeza on 15/10/2016.
 */
@Component
public class IdentityAuthenticationProvider implements AuthenticationProvider {
    private static final Authentication CANNOT_AUTHENTICATE_DETAILS = null;

    @Autowired
    private UserService userService;

    public IdentityAuthenticationProvider() {
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object details = authentication.getDetails();
        if(!(details instanceof IdentityHeaderDetails)) {
            return CANNOT_AUTHENTICATE_DETAILS;
        } else {
            IdentityHeaderDetails identityDetails = (IdentityHeaderDetails)details;
            if(!this.userService.isValid(identityDetails)) {
                throw new AuthenticationCredentialsNotFoundException("Authentication failed");
            } else {
                authentication.setAuthenticated(true);
                return authentication;
            }
       }
    }

    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(PreAuthenticatedAuthenticationToken.class);
    }
}
