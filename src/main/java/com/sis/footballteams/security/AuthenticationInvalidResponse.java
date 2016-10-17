package com.sis.footballteams.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationInvalidResponse implements AuthenticationEntryPoint {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader("Content-Type", "application/json");
        outputStream.println(messageToJson("Invalid Credentials"));
        response.setStatus(HttpStatus.FORBIDDEN.value());
        outputStream.close();

    }

    private String messageToJson(String message) throws IOException {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setSuccess(false);
        responseMessage.setMessage(message);
        responseMessage.setErrorCode(HttpStatus.FORBIDDEN.value());
        return objectMapper.writeValueAsString(responseMessage);
    }
}
