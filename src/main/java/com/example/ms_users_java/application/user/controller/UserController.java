package com.example.ms_users_java.application.user.controller;

import com.example.ms_users_java.application.user.dto.CreateUserRequest;
import com.example.ms_users_java.domain.user.model.User;
import com.example.ms_users_java.domain.user.service.CreateUserService;
import com.example.ms_users_java.domain.user.service.FindAllUsersService;
import com.example.ms_users_java.shared.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final CreateUserService createUserService;
    private final FindAllUsersService findAllUsersService;

    public UserController(CreateUserService createUserService,
                          FindAllUsersService findAllUsersService) {
        this.createUserService = createUserService;
        this.findAllUsersService = findAllUsersService;
    }

    // Define endpoints here, e.g., for creating a user

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> users = findAllUsersService.execute();

        ApiResponse<List<User>> response = new ApiResponse<>("Users retrieved successfully", users);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody CreateUserRequest request) {
        User createdUser = createUserService.execute(request);

        ApiResponse<User> response = new ApiResponse<>("User created successfully", createdUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
