package com.example.fitnessapp.web.controller;

import com.example.fitnessapp.model.ExerciseSchedule;
import com.example.fitnessapp.service.BMIService;
import com.example.fitnessapp.service.impl.BMIServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/bmi")
public class BMIController{
    private final BMIServiceImpl bmiService;

    public BMIController(BMIServiceImpl bmiService) {
        this.bmiService = bmiService;
    }

    @GetMapping
    public String getBMIPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "BMI-calculator");
        return "master-template";
    }

    @PostMapping("/bmi/{id}")
    public String create(@RequestParam float weight, @RequestParam float height, Model model) {
        this.bmiService.create(weight, height);
        float bmi=weight/(height*height);
        model.addAttribute("calculate", bmi);
        model.addAttribute("bodyContent", "BMI-calculator");
        return "master-template";
    }
}

