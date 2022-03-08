package com.example.fitnessapp.model.exceptions;

public class InvalidBMIIdException extends Exception{
    public InvalidBMIIdException(){
        super("There is no BMI with this Id");
    }
}
