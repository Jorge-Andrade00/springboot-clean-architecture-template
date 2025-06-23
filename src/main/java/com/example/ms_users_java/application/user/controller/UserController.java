package com.example.ms_users_java.application.user.controller;

import com.example.ms_users_java.application.user.dto.AuthRequest;
import com.example.ms_users_java.application.user.dto.CreateUserRequest;
import com.example.ms_users_java.domain.user.model.User;
import com.example.ms_users_java.domain.user.service.CreateUserService;
import com.example.ms_users_java.domain.user.service.FindAllUsersService;
import com.example.ms_users_java.domain.user.service.FindUserByEmail;
import com.example.ms_users_java.domain.user.service.GenerateToken;
import com.example.ms_users_java.shared.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "Operations related to users")
public class UserController {
    private final CreateUserService createUserService;
    private final FindAllUsersService findAllUsersService;
    private final FindUserByEmail findUserByEmail;
    private final GenerateToken generateToken;

    public UserController(CreateUserService createUserService,
                          FindAllUsersService findAllUsersService,
                          FindUserByEmail findUserByEmail,
                          GenerateToken generateToken
    ) {
        this.createUserService = createUserService;
        this.findAllUsersService = findAllUsersService;
        this.findUserByEmail = findUserByEmail;
        this.generateToken = generateToken;
    }

    // Define endpoints here, e.g., for creating a user
    // [GET]
    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> users = findAllUsersService.execute();

        ApiResponse<List<User>> response = new ApiResponse<>("Users retrieved successfully", users);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get user by email", description = "Retrieve a user by their email address")
    @GetMapping("/{email}")
    public ResponseEntity<ApiResponse<User>> getUserByEmail(@PathVariable String email) {
        User user = findUserByEmail.execute(email);

        ApiResponse<User> response = new ApiResponse<>("User retrieved successfully", user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // [POST]
    @Operation(summary = "Create a new user", description = "Create a new user with the provided details")
    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody CreateUserRequest request) {
        User createdUser = createUserService.execute(request);

        ApiResponse<User> response = new ApiResponse<>("User created successfully", createdUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Generate token for user", description = "Generate a token for the user with the provided email and password")
    @PostMapping("/token")
    public ResponseEntity<ApiResponse<String>> generateToken(@RequestBody AuthRequest request) {
        String token = generateToken.execute(request);

        ApiResponse<String> response = new ApiResponse<>("Token generated successfully", token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
