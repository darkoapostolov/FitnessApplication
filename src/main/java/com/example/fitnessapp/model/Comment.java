package com.example.fitnessapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    private String content;

    @ManyToOne
    Exercise exercise;

    public Comment(User user, String content, Exercise exercise) {
        this.user = user;
        this.content = content;
        this.exercise=exercise;
    }

    public Comment() {
    }
}
