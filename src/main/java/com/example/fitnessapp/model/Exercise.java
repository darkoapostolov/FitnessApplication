package com.example.fitnessapp.model;

import lombok.Data;
import com.example.fitnessapp.model.enumerations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Exercise {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int difficulty;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    private String description;

    private String image;

    @OneToMany
    private List<Comment> comments;

    private int likes;

    private int dislikes;

    public Exercise(String name, int difficulty, Type type, String description, String image, List<Comment> comments, int likes, int dislikes) {
        this.name = name;
        this.difficulty = difficulty;
        this.type = type;
        this.description = description;
        this.image = image;
        this.comments = comments;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Exercise(Exercise byId) {
    }

    public Exercise() {

    }
}
