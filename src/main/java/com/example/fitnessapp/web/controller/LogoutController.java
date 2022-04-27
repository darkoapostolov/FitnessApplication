package com.example.fitnessapp.web.controller;

import com.example.fitnessapp.service.SpotifyLinkService;
import com.example.fitnessapp.service.impl.SpotifyLinkServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    private final SpotifyLinkService service;

    public LogoutController(SpotifyLinkServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
