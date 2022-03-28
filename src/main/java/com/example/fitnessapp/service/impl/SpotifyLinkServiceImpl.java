package com.example.fitnessapp.service.impl;

import com.example.fitnessapp.model.SpotifyLink;
import com.example.fitnessapp.model.exceptions.InvalidSpotifyLinkIdException;
import com.example.fitnessapp.repository.SpotifyLinkRepository;
import com.example.fitnessapp.service.SpotifyLinkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotifyLinkServiceImpl implements SpotifyLinkService {
    private SpotifyLinkRepository repository;

    public SpotifyLinkServiceImpl(SpotifyLinkRepository repository) {
        this.repository = repository;
    }

    public List<SpotifyLink> findAll() {
        return repository.findAll();
    }

    public SpotifyLink findById(Long id) throws InvalidSpotifyLinkIdException {
        return repository.findById(id).orElseThrow(InvalidSpotifyLinkIdException::new);
    }

    public SpotifyLink findByName(String name) {
        return repository.findByName(name);
    }
}
