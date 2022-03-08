package com.example.fitnessapp.service;

import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.ExerciseSchedule;
import com.example.fitnessapp.model.exceptions.InvalidExerciseScheduleIdException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExerciseScheduleService {
    List<ExerciseSchedule> listAll();
    ExerciseSchedule findById(Long Id) throws InvalidExerciseScheduleIdException;
    ExerciseSchedule create(List<Exercise> exercises);
    ExerciseSchedule edit(Long id, List<Exercise> exercises) throws InvalidExerciseScheduleIdException;
    ExerciseSchedule delete(Long id) throws InvalidExerciseScheduleIdException;
    ExerciseSchedule addExercise(Long id, Exercise exercise) throws InvalidExerciseScheduleIdException;
}
