package com.sis.footballteams.service;

import com.sis.footballteams.security.IdentityHeaderDetails;
import org.springframework.security.core.AuthenticationException;

public interface UserService {

    boolean isValid(IdentityHeaderDetails var1) throws AuthenticationException;
}
