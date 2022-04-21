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

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Exercise> exercises;

    public ExerciseSchedule(String name, String difficulty, List<Exercise> exercises, Type type) {
        this.name = name;
        this.difficulty = difficulty;
        this.exercises = exercises;
        this.type=type;
    }

    public ExerciseSchedule() {
    }
}
