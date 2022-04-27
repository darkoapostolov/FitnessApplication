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
    SpotifyLink create(String name, String link, String image);
    SpotifyLink edit(Long id, String link, String name, String image) throws InvalidSpotifyLinkIdException;
    SpotifyLink delete(Long id) throws InvalidSpotifyLinkIdException;
}
