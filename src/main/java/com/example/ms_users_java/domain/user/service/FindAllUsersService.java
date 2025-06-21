package com.example.ms_users_java.domain.user.service;

import com.example.ms_users_java.domain.user.model.User;
import com.example.ms_users_java.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllUsersService {
    private final UserRepository userRepository;

    public FindAllUsersService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> execute() {
        // Retrieve all users from the repository
        return userRepository.findAll();
    }
}
