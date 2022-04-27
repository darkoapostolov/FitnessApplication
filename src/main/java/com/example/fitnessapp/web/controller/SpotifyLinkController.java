package com.example.fitnessapp.web.controller;

import com.example.fitnessapp.model.SpotifyLink;
import com.example.fitnessapp.model.exceptions.InvalidSpotifyLinkIdException;
import com.example.fitnessapp.service.SpotifyLinkService;
import com.example.fitnessapp.service.impl.SpotifyLinkServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/spotify")
public class SpotifyLinkController {

    private final SpotifyLinkService service;

    public SpotifyLinkController(SpotifyLinkServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/createForm")
    public String createForm(Model model){
        model.addAttribute("bodyContent", "spotify-form");
        model.addAttribute("spLinks", service.findAll());
        return "master-template";
    }

    @GetMapping("/editForm/{id}")
    public String editForm(@PathVariable Long id, Model model) throws InvalidSpotifyLinkIdException {
        model.addAttribute("spotifyLink", service.findById(id));
        model.addAttribute("spLinks", service.findAll());
        model.addAttribute("bodyContent", "spotify-form");
        return "master-template";
    }

    @PostMapping("/create")
    public String create (@RequestParam(required = false) Long id, @RequestParam String name, @RequestParam String link, @RequestParam String image) throws InvalidSpotifyLinkIdException {
        if(id==null) {
            service.create(name, link, image);
        }else {
            service.edit(id, name, link, image);
        }
        return "redirect:/schedules";
    }

    @DeleteMapping("/delete/{id}")
    public String delete (@PathVariable Long id) throws InvalidSpotifyLinkIdException {
        service.delete(id);
        return "redirect:/schedules";
    }
}
