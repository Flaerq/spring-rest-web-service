package com.spring.web.service.project.service.impls;

import com.spring.web.service.project.model.UserEntity;
import com.spring.web.service.project.mappers.Mapper;
import com.spring.web.service.project.repositories.RoleRepository;
import com.spring.web.service.project.repositories.UserRepository;
import com.spring.web.service.project.service.UserService;
import com.spring.web.service.project.utils.Utils;
import com.spring.web.service.project.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private Mapper<UserEntity, UserDto> userMapper;
    private Utils utils;
    private BCryptPasswordEncoder passwordEncoder;

    private UserServiceImpl(UserRepository userRepository,
                            RoleRepository roleRepository, Mapper<UserEntity, UserDto> userMapper,
                            Utils utils,
                            BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.utils = utils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserDto user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(
                (u) -> {
                    throw new RuntimeException("User with this email already exists");
                }
        );

        String publicUserId = utils.generatePublicUserId(40);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());

        user.setEncryptedPassword(encryptedPassword);
        user.setUserId(publicUserId);
        user.setRoles(Collections.singletonList(roleRepository.findByName("USER").get()));

        UserEntity savedUser = userRepository.save(userMapper.mapFrom(user));

        return userMapper.mapTo(savedUser);


    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::mapTo);
    }

}
