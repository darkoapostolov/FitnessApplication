package com.example.fitnessapp.service;

import com.example.fitnessapp.model.SpotifyLink;
import com.example.fitnessapp.model.exceptions.InvalidSpotifyLinkIdException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpotifyLinkService {
    List<SpotifyLink> findAll();
    SpotifyLink findById(Long id) throws InvalidSpotifyLinkIdException;
    SpotifyLink findByName(String name);
}
