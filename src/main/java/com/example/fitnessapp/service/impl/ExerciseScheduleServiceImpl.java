package com.example.fitnessapp.service.impl;

import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.ExerciseSchedule;
import com.example.fitnessapp.model.enumerations.Type;
import com.example.fitnessapp.model.exceptions.InvalidExerciseIdException;
import com.example.fitnessapp.model.exceptions.InvalidExerciseScheduleIdException;
import com.example.fitnessapp.repository.ExerciseRepository;
import com.example.fitnessapp.repository.ExerciseScheduleRepository;
import com.example.fitnessapp.service.ExerciseScheduleService;
import com.example.fitnessapp.service.ExerciseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseScheduleServiceImpl implements ExerciseScheduleService {

    private final ExerciseScheduleRepository repository;
    private final ExerciseServiceImpl exerciseService;

    public ExerciseScheduleServiceImpl(ExerciseScheduleRepository repository, ExerciseServiceImpl exerciseRepository) {
        this.repository = repository;
        this.exerciseService = exerciseRepository;
    }

    @Override
    public List<ExerciseSchedule> listAll() {
        return repository.findAll();
    }

    @Override
    public List<Exercise> listExercises(Long id) throws InvalidExerciseScheduleIdException {
        ExerciseSchedule exerciseSchedule = repository.findById(id).orElseThrow(InvalidExerciseScheduleIdException::new);
        return exerciseSchedule.getExercises();
    }

    @Override
    public ExerciseSchedule findById(Long Id) throws InvalidExerciseScheduleIdException {
        return repository.findById(Id).orElseThrow(InvalidExerciseScheduleIdException::new);
    }

    @Override
    public ExerciseSchedule create(String name, String difficulty, String image, List<Exercise> exercises, Type type) {
        ExerciseSchedule exerciseSchedule = new ExerciseSchedule(name, difficulty, image, exercises, type);
        repository.save(exerciseSchedule);
        return exerciseSchedule;
    }

    @Override
    public ExerciseSchedule edit(Long id, String name,String difficulty, String image, List<Exercise> exercises, Type type) throws InvalidExerciseScheduleIdException {
        ExerciseSchedule exerciseSchedule = repository.findById(id).orElseThrow(InvalidExerciseScheduleIdException::new);
        exerciseSchedule.setExercises(exercises);
        exerciseSchedule.setType(type);
        exerciseSchedule.setImage(image);
        exerciseSchedule.setDifficulty(difficulty);
        repository.save(exerciseSchedule);
        return exerciseSchedule;
    }

    //String name, int reps, int difficulty, Type type, String description, String image, List<Comment> comments, int likes, int dislikes
    @Override
    public ExerciseSchedule delete(Long id) throws InvalidExerciseScheduleIdException {
        ExerciseSchedule exerciseSchedule = repository.findById(id).orElseThrow(InvalidExerciseScheduleIdException::new);
//        for (int i=0;i<exerciseSchedule.getExercises().size();i++){
//            Exercise exercise = exerciseSchedule.getExercises().get(i);
//            exerciseService.create(exercise.getName(),exercise.getReps(),exercise.getDifficulty(),exercise.getType(),exercise.getDescription(),exercise.getImage(),new ArrayList<>(),exercise.getLikes(),exercise.getDislikes());
//        }
        repository.delete(exerciseSchedule);
        return exerciseSchedule;
    }

    @Override
    public ExerciseSchedule addExercise(Long id, Long idEx) throws InvalidExerciseScheduleIdException, InvalidExerciseIdException {
        ExerciseSchedule exerciseSchedule = repository.findById(id).orElseThrow(InvalidExerciseScheduleIdException::new);
        Exercise e = exerciseService.findById(idEx);
        exerciseSchedule.getExercises().add(e);
        repository.save(exerciseSchedule);
        return exerciseSchedule;
    }
}