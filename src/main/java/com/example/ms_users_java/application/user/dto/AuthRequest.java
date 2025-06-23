package com.example.ms_users_java.application.user.dto;

import lombok.Getter;

@Getter
public class AuthRequest {
    private String email;
    private String password;
}
