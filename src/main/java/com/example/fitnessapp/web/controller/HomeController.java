package com.example.fitnessapp.web.controller;

import com.example.fitnessapp.service.SpotifyLinkService;
import com.example.fitnessapp.service.impl.SpotifyLinkServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    private final SpotifyLinkService service;

    public HomeController(SpotifyLinkServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("spLinks", service.findAll());
        model.addAttribute("bodyContent", "home");
        return "master-template";
    }

    @GetMapping("/about")
    public String getAboutPage(Model model) {
        model.addAttribute("spLinks", service.findAll());
        model.addAttribute("bodyContent", "about");
        return "master-template";
    }

    @GetMapping("/contact")
    public String getContactPage(Model model) {
        model.addAttribute("spLinks", service.findAll());
        model.addAttribute("bodyContent", "contact");
        return "master-template";
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("spLinks", service.findAll());
        model.addAttribute("bodyContent", "access_denied");
        return "master-template";
    }

}
