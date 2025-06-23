package com.example.ms_users_java.domain.user.service;

import com.example.ms_users_java.application.user.dto.CreateUserRequest;
import com.example.ms_users_java.domain.user.model.User;
import com.example.ms_users_java.domain.user.repository.UserRepository;
import com.example.ms_users_java.shared.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateUserService {

    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(CreateUserRequest request) {
        // Check if the user already exists
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new AppException("User Creation failed", HttpStatus.CONFLICT);
        }

        // Create a new user
        User user = new User(request.getEmail(), request.getPassword());

        // Save the user to the repository
        return userRepository.save(user);
    }

    ;
}
