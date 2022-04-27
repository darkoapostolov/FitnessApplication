package com.example.fitnessapp.web.controller;

import com.example.fitnessapp.model.BMI;
import com.example.fitnessapp.service.SpotifyLinkService;
import com.example.fitnessapp.service.impl.BMIServiceImpl;
import com.example.fitnessapp.service.impl.SpotifyLinkServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/bmi")
public class BMIController{
    private final BMIServiceImpl bmiService;
    private final SpotifyLinkService service;

    public BMIController(BMIServiceImpl bmiService, SpotifyLinkServiceImpl service) {
        this.bmiService = bmiService;
        this.service = service;
    }

    @GetMapping
    public String getBMIPage(HttpServletRequest request, @RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("spLinks", service.findAll());
        model.addAttribute("calc", request.getSession().getAttribute("calc"));
        model.addAttribute("bodyContent", "BMI-calculator");
        return "master-template";
    }

    @PostMapping("/calculate")
    public String create(HttpServletRequest request, @RequestParam float weight, @RequestParam float height) {
        BMI bmi = bmiService.create(weight, height);
        request.getSession().setAttribute("calc", bmi.getCalculate());
        return "redirect:/bmi";
    }
}

