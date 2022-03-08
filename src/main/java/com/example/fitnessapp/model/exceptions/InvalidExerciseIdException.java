package com.example.fitnessapp.model.exceptions;

public class InvalidExerciseIdException extends Exception{
    public InvalidExerciseIdException(){
        super("There is no exercise with this Id");
    }
}
