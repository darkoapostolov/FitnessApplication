package com.example.fitnessapp.service;

import com.example.fitnessapp.model.Comment;
import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.enumerations.Type;
import com.example.fitnessapp.model.exceptions.InvalidExerciseIdException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExerciseService {
    public Exercise findById(Long id) throws InvalidExerciseIdException;
    public Exercise findByName(String name);
    public Exercise findByType(Type type);
    public Exercise findByDifficulty(int difficulty);
    public Exercise findByNumLikes(int likes);
    public Exercise findByNumDislikes(int dislikes);
    public Exercise create(String name, int difficulty, Type type, String description, String image, List<Comment> comments, int likes, int dislikes);
    public Exercise edit(Long id, String name, int difficulty, Type type, String description, String image, List<Comment> comments, int likes, int dislikes) throws InvalidExerciseIdException;
    public Exercise delete(Long id) throws InvalidExerciseIdException;
}
