package com.spring.web.service.project.service;

import com.spring.web.service.project.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService{

    UserDto createUser(UserDto user);

    Optional<UserDto> findByEmail(String email);
}
