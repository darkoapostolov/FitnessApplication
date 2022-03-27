package com.example.fitnessapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
public class ExerciseSchedule {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String difficulty;

    @ManyToMany
    private List<Exercise> exercises;

    public ExerciseSchedule(String name, String difficulty, List<Exercise> exercises) {
        this.name = name;
        this.difficulty = difficulty;
        this.exercises = exercises;
    }

    public ExerciseSchedule() {
    }
}
