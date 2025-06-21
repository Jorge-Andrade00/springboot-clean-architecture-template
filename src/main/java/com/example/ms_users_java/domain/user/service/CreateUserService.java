package com.example.ms_users_java.domain.user.service;

import com.example.ms_users_java.application.user.dto.CreateUserRequest;
import com.example.ms_users_java.domain.user.model.User;
import com.example.ms_users_java.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateUserService {

    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(CreateUserRequest request) {
        // Check if the user already exists
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        // Create a new user
        User user = new User(request.getEmail(), request.getPassword());

        // Save the user to the repository
        return userRepository.save(user);
    };
}
