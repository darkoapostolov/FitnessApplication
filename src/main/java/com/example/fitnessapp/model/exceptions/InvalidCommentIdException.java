package com.example.fitnessapp.model.exceptions;

public class InvalidCommentIdException extends Exception{
    public InvalidCommentIdException(){
        super("There is no comment with that Id");
    }
}
