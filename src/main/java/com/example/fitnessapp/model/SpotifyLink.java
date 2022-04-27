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

    public SpotifyLink(String name, String link, String image) {
        this.name = name;
        this.link = link;
        this.image = image;
    }

    public SpotifyLink() {
    }
}

