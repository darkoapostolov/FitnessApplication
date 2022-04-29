package com.example.fitnessapp.model;

import com.example.fitnessapp.model.enumerations.Weights;
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

    int reps;

    Weights weights;

    private int difficulty;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    private String description;

    private String image;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Comment> comments;

    private int likes;

    private int dislikes;

    public Exercise(String name, int reps, int difficulty, Weights weights, Type type, String description, String image, List<Comment> comments, int likes, int dislikes) {
        this.name = name;
        this.reps = reps;
        this.difficulty = difficulty;
        this.weights=weights;
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
