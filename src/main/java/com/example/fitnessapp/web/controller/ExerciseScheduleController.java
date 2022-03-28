package com.example.fitnessapp.web.controller;

import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.ExerciseSchedule;
import com.example.fitnessapp.model.exceptions.InvalidExerciseScheduleIdException;
import com.example.fitnessapp.service.ExerciseScheduleService;
import com.example.fitnessapp.service.ExerciseService;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/schedules")
public class ExerciseScheduleController {
    private final ExerciseService exerciseService;
    private final ExerciseScheduleService exerciseScheduleService;

    public ExerciseScheduleController(ExerciseService exerciseService, ExerciseScheduleService exerciseScheduleService) {
        this.exerciseService = exerciseService;
        this.exerciseScheduleService = exerciseScheduleService;
    }


    @GetMapping
    public String getSchedulesPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<ExerciseSchedule> schedules = this.exerciseScheduleService.listAll();
        model.addAttribute("schedules", schedules);
        model.addAttribute("bodyContent", "schedule-list");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) throws InvalidExerciseScheduleIdException {
        this.exerciseScheduleService.delete(id);
        return "redirect:/schedules";
    }

    @GetMapping("/edit-schedule/{id}")
    public String editSchedulePage(@PathVariable Long id, Model model) throws InvalidExerciseScheduleIdException {
            ExerciseSchedule schedule = this.exerciseScheduleService.findById(id);
            model.addAttribute("schedule", schedule);
            model.addAttribute("bodyContent", "add-product");
            return "master-template";
    }

    @GetMapping("/add-schedule")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addSchedulePage(Model model) {
       List<Exercise> exercises = this.exerciseService.findAll();
       model.addAttribute("excercises", exercises);
       model.addAttribute("bodyContent", "add-exercise-schedule");
       return "master-template";
    }

    @PostMapping("/add")
    public String saveSchedule(
            @RequestParam(required = false) Long id,
            String name,
            String difficulty) throws InvalidExerciseScheduleIdException {
        if (id != null) {
            this.exerciseScheduleService.edit(id, name ,difficulty, exerciseScheduleService.findById(id).getExercises());
        } else {
//            this.exerciseScheduleService.addExercise(id, );
        }
        return "redirect:/schedules";
    }
}
