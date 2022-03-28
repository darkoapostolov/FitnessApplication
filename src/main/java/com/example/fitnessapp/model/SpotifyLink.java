package com.example.fitnessapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class SpotifyLink {
    @Id
    @GeneratedValue
    private Long Id;
    private String link;
    private String name;
    private String image;

    public SpotifyLink(Long id, String link, String name, String image) {
        Id = id;
        this.link = link;
        this.name = name;
        this.image = image;
    }

    public SpotifyLink() {
    }
}

