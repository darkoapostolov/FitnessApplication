package com.example.fitnessapp.service;

import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.ExerciseSchedule;
import com.example.fitnessapp.model.exceptions.InvalidExerciseScheduleIdException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExerciseScheduleService {
    public List<ExerciseSchedule> listAll();
    public ExerciseSchedule findById(Long Id) throws InvalidExerciseScheduleIdException;
    public ExerciseSchedule create(List<Exercise> exercises);
    public ExerciseSchedule edit(Long id, List<Exercise> exercises) throws InvalidExerciseScheduleIdException;
    public ExerciseSchedule delete(Long id) throws InvalidExerciseScheduleIdException;
}
