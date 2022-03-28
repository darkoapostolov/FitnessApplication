package com.example.fitnessapp.model.exceptions;

public class InvalidSpotifyLinkIdException extends Exception {
    public InvalidSpotifyLinkIdException(){
        super("There is no spotify link with this Id");
    }
}
