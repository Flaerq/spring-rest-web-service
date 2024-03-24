package com.spring.web.service.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class JwtTokenResponse {

    private String userId;

    private String token;
}
