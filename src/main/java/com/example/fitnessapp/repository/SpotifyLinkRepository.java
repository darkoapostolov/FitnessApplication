package com.example.fitnessapp.repository;

import com.example.fitnessapp.model.SpotifyLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotifyLinkRepository extends JpaRepository<SpotifyLink, Long> {
    SpotifyLink findByName(String name);
}
