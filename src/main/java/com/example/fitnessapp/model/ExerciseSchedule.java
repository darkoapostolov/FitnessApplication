package com.example.fitnessapp.model;

import com.example.fitnessapp.model.enumerations.Type;
import com.example.fitnessapp.model.enumerations.Weights;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ExerciseSchedule {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String difficulty;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    private String image;

    private Weights weights;

    @ManyToMany(cascade=CascadeType.PERSIST)
    private List<Exercise> exercises;

    private String username;

    public ExerciseSchedule(String username, String name, String difficulty, Weights weights, String image, List<Exercise> exercises, Type type) {
        this.username=username;
        this.name = name;
        this.difficulty = difficulty;
        this.weights=weights;
        this.image=image;
        this.exercises = exercises;
        this.type=type;
    }

    public ExerciseSchedule() {
    }
}
