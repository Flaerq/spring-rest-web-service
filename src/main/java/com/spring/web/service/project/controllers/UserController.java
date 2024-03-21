package com.spring.web.service.project.controllers;


import com.spring.web.service.project.dto.UserDetailsRequest;
import com.spring.web.service.project.dto.UserDetailsResponse;
import com.spring.web.service.project.service.UserService;
import com.spring.web.service.project.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    private UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDetailsResponse> createUser(@RequestBody UserDetailsRequest userDetailsRequest){
        UserDetailsResponse response = new UserDetailsResponse();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetailsRequest,userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(userDto, response);

        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }
}
