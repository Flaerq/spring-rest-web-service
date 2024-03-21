package com.spring.web.service.project.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.web.service.project.dto.UserDto;
import com.spring.web.service.project.dto.UserLoginRequest;
import com.spring.web.service.project.mappers.Mapper;
import com.spring.web.service.project.model.UserEntity;
import com.spring.web.service.project.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private Mapper<UserEntity, UserDto> userMapper;
    private JwtService jwtService;

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, Mapper<UserEntity, UserDto> userMapper, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            UserLoginRequest userLoginRequest = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userLoginRequest.getEmail(), userLoginRequest.getPassword());

            return authenticationManager.authenticate(token);

        } catch (IOException exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                         FilterChain filterChain, Authentication authentication) throws AuthenticationException {

        UserLoginRequest loginRequest = (UserLoginRequest)authentication.getPrincipal();

        UserDto bdUser = userService.findByEmail(loginRequest.getEmail()).get();

        CustomUserDetails userDetails = new CustomUserDetails(userMapper.mapFrom(bdUser));

        String token = jwtService.generateToken(userDetails);

        response.addHeader(SecurityConstants.AUTH_HEADER, SecurityConstants.BEARER_PREFIX + token);
    }
}


