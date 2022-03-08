package com.example.fitnessapp.service;

import com.example.fitnessapp.model.User;

public interface AuthService {
    User login(String username, String password);
}
