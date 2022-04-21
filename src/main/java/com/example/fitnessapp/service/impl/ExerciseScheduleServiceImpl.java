package com.example.fitnessapp.service.impl;

import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.ExerciseSchedule;
import com.example.fitnessapp.model.enumerations.Type;
import com.example.fitnessapp.model.exceptions.InvalidExerciseIdException;
import com.example.fitnessapp.model.exceptions.InvalidExerciseScheduleIdException;
import com.example.fitnessapp.repository.ExerciseRepository;
import com.example.fitnessapp.repository.ExerciseScheduleRepository;
import com.example.fitnessapp.service.ExerciseScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseScheduleServiceImpl implements ExerciseScheduleService {

    private final ExerciseScheduleRepository repository;
    private final ExerciseServiceImpl exerciseService;

    public ExerciseScheduleServiceImpl(ExerciseScheduleRepository repository, ExerciseRepository exerciseRepository, ExerciseServiceImpl exerciseService) {
        this.repository = repository;
        this.exerciseService = exerciseService;
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
    public ExerciseSchedule create(String name, String difficulty, List<Exercise> exercises, Type type) {
        ExerciseSchedule exerciseSchedule = new ExerciseSchedule(name, difficulty, exercises, type);
        repository.save(exerciseSchedule);
        return exerciseSchedule;
    }

    @Override
    public ExerciseSchedule edit(Long id, String name, String difficulty, List<Exercise> exercises, Type type) throws InvalidExerciseScheduleIdException {
        ExerciseSchedule exerciseSchedule = repository.findById(id).orElseThrow(InvalidExerciseScheduleIdException::new);
        exerciseSchedule.setExercises(exercises);
        exerciseSchedule.setType(type);
        repository.save(exerciseSchedule);
        return exerciseSchedule;
    }

    @Override
    public ExerciseSchedule delete(Long id) throws InvalidExerciseScheduleIdException {
        ExerciseSchedule exerciseSchedule = repository.findById(id).orElseThrow(InvalidExerciseScheduleIdException::new);
        repository.delete(exerciseSchedule);
        return exerciseSchedule;
    }

    @Override
    public ExerciseSchedule addExercise(Long id, Long idEx) throws InvalidExerciseScheduleIdException, InvalidExerciseIdException {
        ExerciseSchedule exerciseSchedule = repository.findById(id).orElseThrow(InvalidExerciseScheduleIdException::new);
        exerciseSchedule.getExercises().add(new Exercise(exerciseService.findById(id)));
        repository.save(exerciseSchedule);
        return exerciseSchedule;
    }
}