package com.example.fitnessapp.service.impl;

import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.ExerciseSchedule;
import com.example.fitnessapp.model.exceptions.InvalidExerciseScheduleIdException;
import com.example.fitnessapp.repository.ExerciseScheduleRepository;
import com.example.fitnessapp.service.ExerciseScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseScheduleServiceImpl implements ExerciseScheduleService {

    private final ExerciseScheduleRepository repository;

    public ExerciseScheduleServiceImpl(ExerciseScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ExerciseSchedule> listAll() {
        return repository.findAll();
    }

    @Override
    public ExerciseSchedule findById(Long Id) throws InvalidExerciseScheduleIdException {
        return repository.findById(Id).orElseThrow(InvalidExerciseScheduleIdException::new);
    }

    @Override
    public ExerciseSchedule create(List<Exercise> exercises) {
        ExerciseSchedule exerciseSchedule = new ExerciseSchedule(exercises);
        repository.save(exerciseSchedule);
        return exerciseSchedule;
    }

    @Override
    public ExerciseSchedule edit(Long id, List<Exercise> exercises) throws InvalidExerciseScheduleIdException {
        ExerciseSchedule exerciseSchedule = repository.findById(id).orElseThrow(InvalidExerciseScheduleIdException::new);
        exerciseSchedule.setExercises(exercises);
        repository.save(exerciseSchedule);
        return exerciseSchedule;
    }

    @Override
    public ExerciseSchedule delete(Long id) throws InvalidExerciseScheduleIdException {
        ExerciseSchedule exerciseSchedule = repository.findById(id).orElseThrow(InvalidExerciseScheduleIdException::new);
        repository.delete(exerciseSchedule);
        return exerciseSchedule;
    }
}
