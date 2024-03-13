package com.spring.web.service.project.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
