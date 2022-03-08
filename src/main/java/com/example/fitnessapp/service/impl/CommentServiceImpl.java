package com.example.fitnessapp.service.impl;

import com.example.fitnessapp.model.Comment;
import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.User;
import com.example.fitnessapp.model.exceptions.InvalidCommentIdException;
import com.example.fitnessapp.repository.CommentRepository;
import com.example.fitnessapp.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comment> listAll() {
        return repository.findAll();
    }

    @Override
    public List<Comment> listAllBYExercise(Exercise exercise) {
        return repository.findAllByExercise(exercise);
    }

    @Override
    public Comment findById(Long Id) throws InvalidCommentIdException {
        return repository.findById(Id).orElseThrow(InvalidCommentIdException::new);
    }

    @Override
    public List<Comment> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }

    @Override
    public Comment create(User user, String content, Exercise exercise) {
        Comment comment = new Comment(user, content, exercise);
        repository.save(comment);
        return comment;
    }

    @Override
    public Comment edit(Long id, User user, String content, Exercise exercise) throws InvalidCommentIdException {
        Comment comment = repository.findById(id).orElseThrow(InvalidCommentIdException::new);
        comment.setUser(user);
        comment.setContent(content);
        comment.setExercise(exercise);
        repository.save(comment);
        return comment;
    }

    @Override
    public Comment delete(Long id) throws InvalidCommentIdException {
        Comment comment = repository.findById(id).orElseThrow(InvalidCommentIdException::new);
        repository.delete(comment);
        return comment;
    }
}
