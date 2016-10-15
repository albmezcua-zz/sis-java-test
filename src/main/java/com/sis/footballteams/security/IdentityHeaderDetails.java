package com.sis.footballteams.security;

/**
 * Created by lopeza on 15/10/2016.
 */
public class IdentityHeaderDetails {
    private String userUuid;
    private String secret;

    public IdentityHeaderDetails(String userUuid, String secret) {
        this.userUuid = userUuid;
        this.secret = secret;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
