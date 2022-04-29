package com.example.fitnessapp.service;

import com.example.fitnessapp.model.BMI;
import com.example.fitnessapp.model.exceptions.InvalidBMIIdException;
import org.springframework.stereotype.Service;

@Service
public interface BMIService {
    BMI findById(Long Id) throws InvalidBMIIdException;
    BMI create(double height, double weight);
}
