package com.spring.web.service.project.dto;


import com.spring.web.service.project.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {

    private static final long serialVersionUID = 2435230909012397402L;

    private Long id;

    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String encryptedPassword;

    private String emailVerificationToken;

    private Boolean emailVerificationStatus = false;

    private List<Role> roles;
}
