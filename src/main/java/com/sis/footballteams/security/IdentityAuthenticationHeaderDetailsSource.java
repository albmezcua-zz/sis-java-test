package com.sis.footballteams.security;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lopeza on 15/10/2016.
 */
public class IdentityAuthenticationHeaderDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, IdentityHeaderDetails> {
    private static final String APPLICATION_UUID_HEADER = "X-SIS-UserId";
    private static final String APPLICATION_SECRET_HEADER = "Secret";

    public IdentityAuthenticationHeaderDetailsSource() {
    }

    public IdentityHeaderDetails buildDetails(HttpServletRequest httpServletRequest) {
        return new IdentityHeaderDetails(httpServletRequest.getHeader(APPLICATION_UUID_HEADER), httpServletRequest.getHeader(APPLICATION_SECRET_HEADER));
    }

}