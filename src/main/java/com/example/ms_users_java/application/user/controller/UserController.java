package com.example.ms_users_java.application.user.controller;

import com.example.ms_users_java.application.user.dto.CreateUserRequest;
import com.example.ms_users_java.domain.user.model.User;
import com.example.ms_users_java.domain.user.service.CreateUserService;
import com.example.ms_users_java.shared.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final CreateUserService createUserService;

    public UserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    // Define endpoints here, e.g., for creating a user
    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody CreateUserRequest request) {
        User createdUser = createUserService.execute(request);

        ApiResponse<User> response = new ApiResponse<>("User created successfully", createdUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
