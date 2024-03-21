package com.spring.web.service.project.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;


@Component
public class Utils {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random random = new SecureRandom();

    public String generatePublicUserId(int length){
        return generateRandomString(length);
    }

    public String generateRandomString(int length){

        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++){
            randomString.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return randomString.toString();

    }
}
