package com.example.fitnessapp.service;

import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.ExerciseSchedule;
import com.example.fitnessapp.model.enumerations.Type;
import com.example.fitnessapp.model.exceptions.InvalidExerciseIdException;
import com.example.fitnessapp.model.exceptions.InvalidExerciseScheduleIdException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExerciseScheduleService {
    List<ExerciseSchedule> listAll();
    List<Exercise> listExercises(Long id) throws InvalidExerciseScheduleIdException;
    ExerciseSchedule findById(Long Id) throws InvalidExerciseScheduleIdException;
    ExerciseSchedule create(String name, String difficulty, String Image, List<Exercise> exercises, Type type);
    ExerciseSchedule edit(Long id, String name, String difficulty, String Image, List<Exercise> exercises, Type type) throws InvalidExerciseScheduleIdException;
    ExerciseSchedule delete(Long id) throws InvalidExerciseScheduleIdException;
    ExerciseSchedule addExercise(Long id, Long idEx) throws InvalidExerciseScheduleIdException, InvalidExerciseIdException;
}