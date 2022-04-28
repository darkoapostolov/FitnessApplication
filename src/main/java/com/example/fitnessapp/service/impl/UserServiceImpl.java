package com.example.fitnessapp.service.impl;

import com.example.fitnessapp.model.ExerciseSchedule;
import com.example.fitnessapp.model.Role;
import com.example.fitnessapp.model.User;
import com.example.fitnessapp.model.exceptions.InvalidUsernameOrPasswordException;
import com.example.fitnessapp.model.exceptions.UsernameAlreadyExistsException;
import com.example.fitnessapp.repository.UserRepository;
import com.example.fitnessapp.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, Role role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password),role);
        return userRepository.save(user);
    }

    @Override
    public List<ExerciseSchedule> listSchedules(String username) {
        return userRepository.findByUsername(username).get().getExerciseSchedules();
    }

    @Override
    public void addExSchedule(ExerciseSchedule exerciseSchedule, String username) {
        userRepository.findByUsername(username).get().getExerciseSchedules().add(exerciseSchedule);
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }
}
