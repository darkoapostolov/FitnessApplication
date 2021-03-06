package com.example.fitnessapp.service;

import com.example.fitnessapp.model.Comment;
import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.enumerations.Type;
import com.example.fitnessapp.model.enumerations.Weights;
import com.example.fitnessapp.model.exceptions.InvalidExerciseIdException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExerciseService {
    List<Exercise> findAll();
    Exercise findById(Long id) throws InvalidExerciseIdException;
    Exercise findByName(String name);
    Exercise findByType(Type type);
    Exercise findByDifficulty(int difficulty);
    Exercise findByNumLikes(int likes);
    Exercise findByNumDislikes(int dislikes);
    Exercise create(String name, int reps, int difficulty, Weights weights, Type type, String description, String image, List<Comment> comments, int likes, int dislikes);
    Exercise edit(Long id, String name, int reps, int difficulty, Weights weights, Type type, String description, String image, List<Comment> comments, int likes, int dislikes) throws InvalidExerciseIdException;
    Exercise delete(Long id) throws InvalidExerciseIdException;
    Exercise addComment (Long id, Comment comment) throws InvalidExerciseIdException;
    List<Exercise> listByType(Type type);
    int like(Long id) throws InvalidExerciseIdException;
    int dislike(Long id) throws InvalidExerciseIdException;
    void update(Exercise exercise);
    List<Exercise> filterExercises(String name, Weights weights, int difficulty, Type type);
}
