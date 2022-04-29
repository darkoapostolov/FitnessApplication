package com.example.fitnessapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.text.DecimalFormat;

@Data
@Entity
public class BMI {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    private double height;

    private double weight;

    private double calculate;

    private String date;

    public BMI(double height, double weight) {
        this.height = height;
        this.weight = weight;
        this.calculate = weight/((height/100)*(height/100));
    }

    public BMI() {
    }
}
