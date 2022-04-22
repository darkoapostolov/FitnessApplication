package com.example.fitnessapp.model;

import com.example.fitnessapp.model.enumerations.Type;
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

    @ManyToMany(cascade=CascadeType.PERSIST)
    private List<Exercise> exercises;

    public ExerciseSchedule(String name, String difficulty, String image, List<Exercise> exercises, Type type) {
        this.name = name;
        this.difficulty = difficulty;
        this.image=image;
        this.exercises = exercises;
        this.type=type;
    }

    public ExerciseSchedule() {
    }
}
