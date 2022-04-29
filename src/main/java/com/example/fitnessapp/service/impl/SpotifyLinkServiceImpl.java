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

    public SpotifyLink create(String name, String link, String image){
        SpotifyLink s = new SpotifyLink(name,link,image);
        repository.save(s);
        return s;
    }

    public SpotifyLink edit(Long id, String name, String link, String image) throws InvalidSpotifyLinkIdException {
        SpotifyLink s = repository.findById(id).orElseThrow(InvalidSpotifyLinkIdException::new);
        s.setLink(link);
        s.setName(name);
        s.setImage(image);
        repository.save(s);
        return s;
    }
    public SpotifyLink delete(Long id) throws InvalidSpotifyLinkIdException {
        SpotifyLink s = repository.findById(id).orElseThrow(InvalidSpotifyLinkIdException::new);
        repository.delete(s);
        return s;
    }
}
