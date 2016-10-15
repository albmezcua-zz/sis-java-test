package com.sis.footballteams.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.method.HandlerMethod
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod

import java.lang.reflect.Method

final class ExceptionHandlerExceptionResolverBuilder {

    public static ExceptionHandlerExceptionResolver build(MappingJackson2HttpMessageConverter messageConverter,
                                                          ObjectMapper objectMapper,
                                                          Class exceptionHandlerClass) {
        ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
            protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod, Exception exception) {
                Method method = new ExceptionHandlerMethodResolver(exceptionHandlerClass).resolveMethod(exception)
                return new ServletInvocableHandlerMethod(exceptionHandlerClass.newInstance(), method)
            }
        }

        messageConverter.setObjectMapper(objectMapper)
        exceptionResolver.getMessageConverters().add(messageConverter)
        exceptionResolver.afterPropertiesSet()
        return exceptionResolver;
    }

}
