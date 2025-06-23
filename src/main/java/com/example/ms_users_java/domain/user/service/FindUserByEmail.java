package com.example.ms_users_java.domain.user.service;

import com.example.ms_users_java.domain.user.model.User;
import com.example.ms_users_java.domain.user.repository.UserRepository;
import com.example.ms_users_java.shared.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUserByEmail {
    private final UserRepository userRepository;

    public FindUserByEmail(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String email) {
        // Check if the user exists by email
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new AppException("User not found", HttpStatus.NOT_FOUND);
        }

        // Return the user if found
        return user.get();
    }
}
