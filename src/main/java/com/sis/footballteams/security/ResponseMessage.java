package com.sis.footballteams.security;

import lombok.Data;

@Data
public class ResponseMessage {

    private boolean success;

    private String message;

    private int errorCode;
}
