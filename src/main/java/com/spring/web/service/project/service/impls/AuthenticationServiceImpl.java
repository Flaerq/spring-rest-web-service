package com.spring.web.service.project.service.impls;

import com.spring.web.service.project.dto.JwtTokenResponse;
import com.spring.web.service.project.dto.UserLoginRequest;
import com.spring.web.service.project.dto.UserRegisterRequest;
import com.spring.web.service.project.dto.UserDto;
import com.spring.web.service.project.security.CustomUserDetails;
import com.spring.web.service.project.security.JwtService;
import com.spring.web.service.project.service.AuthenticationService;
import com.spring.web.service.project.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtService jwtService;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserService userService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
    }


    @Override
    public JwtTokenResponse login(UserLoginRequest userLoginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userLoginRequest.getEmail(), userLoginRequest.getPassword()
        );
        authenticationManager.authenticate(token);

        UserDto userDto = userService.findByEmail(userLoginRequest.getEmail()).get();
        CustomUserDetails userDetails = new CustomUserDetails(userDto);

        String jwtToken = jwtService.generateToken(userDetails);

        return new JwtTokenResponse(userDto.getUserId(), jwtToken);
    }


}
