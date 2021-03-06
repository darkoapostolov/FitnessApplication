package com.example.fitnessapp.service.impl;

import com.example.fitnessapp.model.Comment;
import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.enumerations.Type;
import com.example.fitnessapp.model.enumerations.Weights;
import com.example.fitnessapp.model.exceptions.InvalidExerciseIdException;
import com.example.fitnessapp.repository.ExerciseRepository;
import com.example.fitnessapp.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository repository;

    public ExerciseServiceImpl(ExerciseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Exercise> findAll() {
        return repository.findAll();
    }

    @Override
    public Exercise findById(Long id) throws InvalidExerciseIdException {
        return repository.findById(id).orElseThrow(InvalidExerciseIdException::new);
    }

    @Override
    public Exercise findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Exercise findByType(Type type) {
        return repository.findByType(type);
    }

    @Override
    public Exercise findByDifficulty(int difficulty) {
        return repository.findByDifficulty(difficulty);
    }

    @Override
    public Exercise findByNumLikes(int likes) {
        return repository.findByLikes(likes);
    }

    @Override
    public Exercise findByNumDislikes(int dislikes) {
        return repository.findByDislikes(dislikes);
    }

    @Override
    public Exercise create(String name, int reps, int difficulty, Weights weights, Type type, String description, String image, List<Comment> comments, int likes, int dislikes) {
        Exercise exercise = new Exercise(name, reps, difficulty, weights, type, description, image, comments, likes, dislikes);
        repository.save(exercise);
        return exercise;
    }

    @Override
    public Exercise edit(Long id, String name,int reps, int difficulty, Weights weights, Type type, String description, String image, List<Comment> comments, int likes, int dislikes) throws InvalidExerciseIdException {
        Exercise exercise = repository.findById(id).orElseThrow(InvalidExerciseIdException::new);
        exercise.setName(name);
        exercise.setDifficulty(difficulty);
        exercise.setWeights(weights);
        exercise.setReps(reps);
        exercise.setType(type);
        exercise.setDescription(description);
        exercise.setImage(image);
        exercise.setComments(comments);
        exercise.setLikes(likes);
        exercise.setDislikes(dislikes);
        repository.save(exercise);
        return exercise;
    }

    @Override
    public Exercise delete(Long id) throws InvalidExerciseIdException {
        Exercise exercise = repository.findById(id).orElseThrow(InvalidExerciseIdException::new);
        repository.delete(exercise);
        return exercise;
    }

    @Override
    public Exercise addComment(Long id, Comment comment) throws InvalidExerciseIdException {
        Exercise exercise = repository.findById(id).orElseThrow(InvalidExerciseIdException::new);
        exercise.getComments().add(comment);
        repository.save(exercise);
        return exercise;
    }

    @Override
    public List<Exercise> listByType(Type type) {
        List<Exercise> list = repository.findAll();
        return (List<Exercise>) list.stream().filter(r->r.getType().equals(type));
    }

    @Override
    public int like(Long id) throws InvalidExerciseIdException {
        Exercise exercise = repository.findById(id).orElseThrow(InvalidExerciseIdException::new);
        int likes = exercise.getLikes();
        likes+=1;
        exercise.setLikes(likes);
        repository.save(exercise);
        return likes;
    }

    @Override
    public int dislike(Long id) throws InvalidExerciseIdException {
        Exercise exercise = repository.findById(id).orElseThrow(InvalidExerciseIdException::new);
        int dislikes = exercise.getDislikes();
        dislikes+=1;
        exercise.setDislikes(dislikes);
        repository.save(exercise);
        return dislikes;
    }

    @Override
    public void update(Exercise exercise) {
        repository.save(exercise);
    }

    @Override
    public List<Exercise> filterExercises(String name, Weights weights, int difficulty, Type type) {
        if (name==""){
            name=null;
        }
        if (name==null && weights==null && difficulty==0 && type==null){
            return repository.findAll();
        }
        else if (name==null && weights==null && difficulty==0){
            return repository.findAllByType(type);
        }
        else if (name==null && weights==null && type==null){
            return repository.findAllByDifficulty(difficulty);
        }
        else if (name==null&& type==null && difficulty==0){
            return repository.findAllByWeights(weights);
        }
        else if (type==null && difficulty==0 && weights==null){
            return repository.findAllByNameLike("%"+name+"%");
        }
        else if (name==null && weights==null){
            return repository.findAllByDifficultyAndType(difficulty, type);
        }
        else if (name==null && type==null){
            return repository.findAllByWeightsAndDifficulty(weights, difficulty);
        }
        else if (weights==null&& type==null){
            return repository.findAllByNameLikeAndDifficulty("%"+name+"%", difficulty);
        }
        else if (type==null && difficulty==0){
            return repository.findAllByWeightsAndNameLike(weights, "%"+name+"%");
        }
        else if (weights==null && difficulty==0){
            return repository.findAllByNameLikeAndType("%"+name+"%", type);
        }
        else if (name==null && difficulty==0){
            return repository.findAllByWeightsAndType(weights, type);
        }
        else if (name==null){
            return repository.findAllByWeightsAndDifficultyAndType(weights, difficulty, type);
        }
        else if (difficulty==0){
            return repository.findAllByWeightsAndNameLikeAndType(weights, "%"+name+"%", type);
        }
        else if (type==null){
            return repository.findAllByWeightsAndNameLikeAndDifficulty(weights, "%"+name+"%", difficulty);
        }
        else if (weights==null){
            return repository.findAllByTypeAndDifficultyAndNameLike(type, difficulty, "%"+name+"%");
        }
        else{
            return repository.findAllByWeightsAndDifficultyAndTypeAndNameLike(weights, difficulty, type, "%"+name+"%");
        }
    }
}
