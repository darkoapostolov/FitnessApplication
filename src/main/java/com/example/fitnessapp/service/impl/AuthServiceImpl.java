package com.example.fitnessapp.service.impl;

import com.example.fitnessapp.model.User;
import com.example.fitnessapp.model.exceptions.InvalidArgumentsException;
import com.example.fitnessapp.model.exceptions.InvalidUserCredentialsException;
import com.example.fitnessapp.repository.UserRepository;
import com.example.fitnessapp.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
