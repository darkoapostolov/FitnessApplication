package com.example.fitnessapp.web.controller;

import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.ExerciseSchedule;
import com.example.fitnessapp.model.User;
import com.example.fitnessapp.model.exceptions.InvalidExerciseIdException;
import com.example.fitnessapp.model.exceptions.InvalidExerciseScheduleIdException;
import com.example.fitnessapp.service.ExerciseScheduleService;
import com.example.fitnessapp.service.ExerciseService;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/exercise-list")
public class ExerciseListController {

    private final ExerciseScheduleService exerciseScheduleService;
    private final ExerciseService exerciseService;

    public ExerciseListController(ExerciseScheduleService exerciseScheduleService, ExerciseService exerciseService) {
        this.exerciseScheduleService =  exerciseScheduleService;
        this.exerciseService =  exerciseService;
    }

    @SneakyThrows
    @GetMapping("/schedules/exercise-list/{id}")
    public String getExerciseList(@PathVariable Long id,
            @RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        ExerciseSchedule exerciseSchedule = this.exerciseScheduleService.findById(id);
        model.addAttribute("exercises", this.exerciseScheduleService.listExercises(exerciseSchedule.getId()));
        model.addAttribute("bodyContent", "exercise-list");
        return "master-template";
    }

    @PostMapping("/schedules/{id}/add-exercise")
    public String addExercise(@PathVariable Long id, Long exerciseId, HttpServletRequest req, Authentication authentication) {
        try {
            Exercise exercise = this.exerciseService.findById(id);
            this.exerciseScheduleService.addExercise(exerciseId, exercise);
            return "redirect:/exercise-list";
        } catch (RuntimeException | InvalidExerciseIdException | InvalidExerciseScheduleIdException exception) {
            return "redirect:/exercise-list?error=" + exception.getMessage();
        }
    }

    @DeleteMapping("/schedules/exercise/{id}/delete/")
    public String deleteExercise(@PathVariable Long id) throws InvalidExerciseIdException {
        this.exerciseService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/schedules/exercise/{id}/edit-form/")
    public String editExercise(@PathVariable Long id, Model model) throws InvalidExerciseIdException {
        Exercise exercise = this.exerciseService.findById(id);
        model.addAttribute("exercise", exercise);
        model.addAttribute("bodyContent", "add-exercise");
        return "master-template";
    }

//    @PostMapping("/add")
//    public String saveExercise(
//            @RequestParam(required = false) Long id,
//            @RequestParam String name,
//            @RequestParam Double price,
//            @RequestParam Integer quantity,
//            @RequestParam Long category,
//            @RequestParam Long manufacturer) {
//        if (id != null) {
//            this.productService.edit(id, name, price, quantity, category, manufacturer);
//        } else {
//            this.productService.save(name, price, quantity, category, manufacturer);
//        }
//        return "redirect:/products";
//    }
}
