package com.spring.web.service.project.service;

import com.spring.web.service.project.dto.JwtTokenResponse;
import com.spring.web.service.project.dto.UserLoginRequest;


public interface AuthenticationService {

    JwtTokenResponse login(UserLoginRequest userLoginRequest);
}
