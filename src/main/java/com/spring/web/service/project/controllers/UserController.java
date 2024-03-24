package com.spring.web.service.project.controllers;


import com.spring.web.service.project.dto.*;
import com.spring.web.service.project.service.AuthenticationService;
import com.spring.web.service.project.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private AuthenticationService authenticationService;

    private UserController(UserService userService, AuthenticationService authenticationService){
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<UserDetailsResponse> createUser(@RequestBody UserRegisterRequest userDetailsRequest){
        UserDetailsResponse response = new UserDetailsResponse();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetailsRequest,userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(userDto, response);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public JwtTokenResponse loginUser(@RequestBody UserLoginRequest userLoginRequest){
        return authenticationService.login(userLoginRequest);

    }

    @GetMapping
    public String test() {
        return "NIGGA";
    }


}
