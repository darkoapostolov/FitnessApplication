package com.example.fitnessapp.service.impl;

import com.example.fitnessapp.model.BMI;
import com.example.fitnessapp.model.exceptions.InvalidBMIIdException;
import com.example.fitnessapp.repository.BMIRepository;
import com.example.fitnessapp.service.BMIService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        LocalDateTime dateTime = LocalDateTime.now();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(dateTime.getDayOfMonth())+"."+String.valueOf(dateTime.getMonthValue())+"."+String.valueOf(dateTime.getYear())+"\n"+String.valueOf(dateTime.getHour())+":"+String.valueOf(dateTime.getMinute())+":"+String.valueOf(dateTime.getSecond()));
        bmi.setDate(stringBuilder.toString());
        repository.save(bmi);
        return bmi;
    }
}
