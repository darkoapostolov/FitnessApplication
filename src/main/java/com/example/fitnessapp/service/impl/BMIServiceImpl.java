package com.example.fitnessapp.service.impl;

import com.example.fitnessapp.model.BMI;
import com.example.fitnessapp.model.exceptions.InvalidBMIIdException;
import com.example.fitnessapp.repository.BMIRepository;
import com.example.fitnessapp.service.BMIService;
import org.springframework.stereotype.Service;

@Service
public class BMIServiceImpl implements BMIService {

    private final BMIRepository repository;

    public BMIServiceImpl(BMIRepository repository) {
        this.repository = repository;
    }

    @Override
    public BMI findById(Long Id) throws InvalidBMIIdException {
        return repository.findById(Id).orElseThrow(InvalidBMIIdException::new);
    }

    @Override
    public BMI create(double height, double weight) {
        BMI bmi = new BMI(height,weight);
        bmi.setCalculate(height/(weight*weight));
        repository.save(bmi);
        return bmi;
    }

    @Override
    public BMI edit(Long id, double height, double weight) throws InvalidBMIIdException {
        BMI bmi = repository.findById(id).orElseThrow(InvalidBMIIdException::new);
        bmi.setHeight(height);
        bmi.setWeight(weight);
        bmi.setCalculate(height/(weight*weight));
        repository.save(bmi);
        return bmi;
    }

    @Override
    public BMI delete(Long id) throws InvalidBMIIdException {
        BMI bmi = repository.findById(id).orElseThrow(InvalidBMIIdException::new);
        repository.delete(bmi);
        return bmi;
    }
}
