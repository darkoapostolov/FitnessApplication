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

    @ManyToMany
    private List<Exercise> exercises;

    public ExerciseSchedule(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public ExerciseSchedule() {
    }
}
