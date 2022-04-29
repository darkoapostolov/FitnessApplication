package com.example.fitnessapp.service;

import com.example.fitnessapp.model.ExerciseSchedule;
import com.example.fitnessapp.model.Role;
import com.example.fitnessapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User register (String username, String password, Role role);
    List<ExerciseSchedule> listSchedules(String username);
    void addExSchedule(ExerciseSchedule exerciseSchedule, String username);
    User findByUsername(String username);
    void update(User user);
}
