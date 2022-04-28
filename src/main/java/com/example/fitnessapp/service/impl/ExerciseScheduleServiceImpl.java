package com.example.fitnessapp.service.impl;

import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.ExerciseSchedule;
import com.example.fitnessapp.model.User;
import com.example.fitnessapp.model.enumerations.Type;
import com.example.fitnessapp.model.enumerations.Weights;
import com.example.fitnessapp.model.exceptions.InvalidExerciseIdException;
import com.example.fitnessapp.model.exceptions.InvalidExerciseScheduleIdException;
import com.example.fitnessapp.repository.ExerciseScheduleRepository;
import com.example.fitnessapp.service.ExerciseScheduleService;
import com.example.fitnessapp.service.ExerciseService;
import com.example.fitnessapp.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ExerciseScheduleServiceImpl implements ExerciseScheduleService {

    private final ExerciseScheduleRepository repository;
    private final ExerciseService exerciseService;
    private final UserService userService;

    public ExerciseScheduleServiceImpl(ExerciseScheduleRepository repository, ExerciseServiceImpl exerciseRepository, UserService userService) {
        this.repository = repository;
        this.exerciseService = exerciseRepository;
        this.userService = userService;
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
    public ExerciseSchedule create(String username, String name, String difficulty, Weights weights, String image, List<Exercise> exercises, Type type) {
        ExerciseSchedule exerciseSchedule = new ExerciseSchedule(username, name, difficulty, weights, image, exercises, type);
        repository.save(exerciseSchedule);
        return exerciseSchedule;
    }

    @Override
    public ExerciseSchedule edit(Long id, String name,String difficulty, Weights weights, String image, List<Exercise> exercises, Type type) throws InvalidExerciseScheduleIdException {
        ExerciseSchedule exerciseSchedule = repository.findById(id).orElseThrow(InvalidExerciseScheduleIdException::new);
        exerciseSchedule.setExercises(exercises);
        exerciseSchedule.setWeights(weights);
        exerciseSchedule.setType(type);
        exerciseSchedule.setImage(image);
        exerciseSchedule.setName(name);
        exerciseSchedule.setDifficulty(difficulty);
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
        Exercise e = exerciseService.findById(idEx);
        exerciseSchedule.getExercises().add(e);
        repository.save(exerciseSchedule);
        return exerciseSchedule;
    }

    @Override
    public ExerciseSchedule removeExercise(Long id, Long idEx) throws InvalidExerciseScheduleIdException, InvalidExerciseIdException {
        ExerciseSchedule exerciseSchedule = repository.findById(id).orElseThrow(InvalidExerciseScheduleIdException::new);
        Exercise exercise = exerciseService.findById(idEx);
        exerciseSchedule.getExercises().remove(exercise);
        repository.save(exerciseSchedule);
        return exerciseSchedule;
    }

    @Override
    public List<ExerciseSchedule> ListByUser(String username, Long id) throws InvalidExerciseScheduleIdException {
        User user = userService.findByUsername(username);
        ExerciseSchedule exerciseSchedule = repository.findById(id).orElseThrow(InvalidExerciseScheduleIdException::new);
        if (user.getUsername().equals(exerciseSchedule.getUsername())) {
            user.getExerciseSchedules().add(exerciseSchedule);
        }
        return user.getExerciseSchedules();
    }
}