package com.example.fitnessapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class BMI {
    @Id
    @GeneratedValue
    private Long id;

    private double height;

    private double weight;

    public BMI(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    public BMI() {
    }
}
