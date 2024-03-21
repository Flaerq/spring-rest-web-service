package com.spring.web.service.project.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTH_HEADER = "Authorization";

}
