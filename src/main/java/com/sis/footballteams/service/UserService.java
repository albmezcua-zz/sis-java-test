package com.sis.footballteams.service;

import com.sis.footballteams.security.IdentityHeaderDetails;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by lopeza on 15/10/2016.
 */
public interface UserService {

    boolean isValid(IdentityHeaderDetails var1) throws AuthenticationException;
}
