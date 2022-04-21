package com.example.fitnessapp.web.controller;

import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.ExerciseSchedule;
import com.example.fitnessapp.model.enumerations.Type;
import com.example.fitnessapp.model.exceptions.InvalidExerciseIdException;
import com.example.fitnessapp.model.exceptions.InvalidExerciseScheduleIdException;
import com.example.fitnessapp.service.ExerciseScheduleService;
import com.example.fitnessapp.service.ExerciseService;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
            model.addAttribute("types", Type.values());
            model.addAttribute("bodyContent", "add-exercise-schedule");
            return "master-template";
    }

    @GetMapping("/schedule/{id}/exercise-list/")
    public String listExercises(@PathVariable Long id, Model model) throws InvalidExerciseIdException, InvalidExerciseScheduleIdException {
        List<Exercise> exercises = exerciseScheduleService.listExercises(id);
        model.addAttribute("exercise", exercises);
        model.addAttribute("bodyContent", "exercise-list");
        return "master-template";
    }

    @GetMapping("/add-schedule")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addSchedulePage(Model model) {
       List<Exercise> exercises = this.exerciseService.findAll();
       model.addAttribute("exercises", exercises);
       model.addAttribute("types", Type.values());
       model.addAttribute("bodyContent", "add-exercise-schedule");
       return "master-template";
    }

    @PostMapping("/add")
    public String saveSchedule(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String difficulty,
            @RequestParam Type type) throws InvalidExerciseScheduleIdException {

        List<Exercise> exercises = new ArrayList<>();
        if (id != null) {
            this.exerciseScheduleService.edit(id, name ,difficulty, exercises, type);
        } else {
            this.exerciseScheduleService.create(name ,difficulty, exercises, type);;
        }
        return "redirect:/schedules";
    }

    @GetMapping("/schedule/{id}/add-exercise")
    public String addExerciseToSchedule(HttpServletRequest req, @PathVariable Long id, Model model) throws InvalidExerciseScheduleIdException {
        List<Exercise> exercises = this.exerciseService.findAll();
        req.getSession().setAttribute("id",id);
        model.addAttribute("schedule",exerciseScheduleService.findById(id));
        model.addAttribute("exercises", exercises);
        model.addAttribute("bodyContent", "add-exercise-to-schedule");
        return "master-template";
    }

    @PostMapping("/addToSchedule")
    public String addToSchedule(@RequestParam Long exId, HttpServletRequest request) throws InvalidExerciseIdException, InvalidExerciseScheduleIdException {
        Long id = Long.parseLong(String.valueOf(request.getSession().getAttribute("id")));
            this.exerciseScheduleService.addExercise(id, exId);
        return "redirect:/schedules";
    }
}
