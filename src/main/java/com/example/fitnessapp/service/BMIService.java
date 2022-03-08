package com.example.fitnessapp.service;

import com.example.fitnessapp.model.BMI;
import com.example.fitnessapp.model.exceptions.InvalidBMIIdException;
import org.springframework.stereotype.Service;

@Service
public interface BMIService {
    public BMI findById(Long Id) throws InvalidBMIIdException;
    public BMI create(double height, double weight);
    public BMI edit(Long id, double height, double weight) throws InvalidBMIIdException;
    public BMI delete(Long id) throws InvalidBMIIdException;
}
