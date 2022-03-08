package com.example.fitnessapp.service;

import com.example.fitnessapp.model.Comment;
import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.User;
import com.example.fitnessapp.model.exceptions.InvalidCommentIdException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    public List<Comment> listAll();
    public List<Comment> listAllBYExercise(Exercise exercise);
    public Comment findById (Long Id) throws InvalidCommentIdException;
    public List<Comment> findAllByUser(User user);
    public Comment create(User user, String content, Exercise exercise);
    public Comment edit(Long id, User user, String content, Exercise exercise) throws InvalidCommentIdException;
    public Comment delete(Long id) throws InvalidCommentIdException;
}
