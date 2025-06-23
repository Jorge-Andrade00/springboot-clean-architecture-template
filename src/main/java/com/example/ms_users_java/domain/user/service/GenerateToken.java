package com.example.ms_users_java.domain.user.service;

import com.example.ms_users_java.application.user.dto.AuthRequest;
import com.example.ms_users_java.domain.user.model.User;
import com.example.ms_users_java.domain.user.repository.UserRepository;
import com.example.ms_users_java.shared.security.JwtUtil;
import com.nimbusds.jose.JOSEException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenerateToken {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public GenerateToken(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public String execute(AuthRequest request) throws JOSEException {
        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isEmpty() || !user.get().getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        // Generate JWT token
        return jwtUtil.generateToken(user.get().getEmail());
    }
}
