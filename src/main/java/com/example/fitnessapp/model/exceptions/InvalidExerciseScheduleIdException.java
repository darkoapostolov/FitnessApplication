package com.example.fitnessapp.model.exceptions;

public class InvalidExerciseScheduleIdException extends Exception{
    public InvalidExerciseScheduleIdException(){
        super("There is no Exercise Schedule with this Id");
    }
}
