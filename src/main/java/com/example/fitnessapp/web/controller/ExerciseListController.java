package com.example.fitnessapp.web.controller;

import com.example.fitnessapp.model.Comment;
import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.enumerations.Type;
import com.example.fitnessapp.model.enumerations.Weights;
import com.example.fitnessapp.model.exceptions.InvalidExerciseIdException;
import com.example.fitnessapp.service.ExerciseScheduleService;
import com.example.fitnessapp.service.ExerciseService;
import com.example.fitnessapp.service.SpotifyLinkService;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/exercise-list")
public class ExerciseListController {

    private final ExerciseScheduleService exerciseScheduleService;
    private final ExerciseService exerciseService;
    private final SpotifyLinkService spotifyLinkService;

    public ExerciseListController(ExerciseScheduleService exerciseScheduleService, ExerciseService exerciseService, SpotifyLinkService spotifyLinkService) {
        this.exerciseScheduleService = exerciseScheduleService;
        this.exerciseService = exerciseService;
        this.spotifyLinkService = spotifyLinkService;
    }

    @SneakyThrows
    @GetMapping()
    public String getExerciseList(
            @RequestParam(required = false) String error,
            HttpServletRequest req,
            Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("spLinks", spotifyLinkService.findAll());
        model.addAttribute("exercises", this.exerciseService.findAll());
        model.addAttribute("bodyContent", "exercise-list");
        return "master-template";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addFormEx(Model model) {
        model.addAttribute("type", Type.values());
        model.addAttribute("bool",Weights.values());
        model.addAttribute("spLinks", spotifyLinkService.findAll());
        model.addAttribute("bodyContent", "add-exercises");
        return "master-template";
    }

    @PostMapping("/add-exercise")
    public String addExercise(@RequestParam(required = false) Long id, String name, int reps, int difficulty, Weights weights, Type type, String description, String image) throws InvalidExerciseIdException {
        if (id != null) {
            Exercise exercise = exerciseService.findById(id);
            List<Comment> comments = exercise.getComments();
            int likes = exercise.getLikes();
            int dislikes = exercise.getDislikes();
            exerciseService.edit(id, name, reps, difficulty, weights, type, description, image, comments, likes, dislikes);
            return "redirect:/exercise-list";
        } else {
            List<Comment> comments = new ArrayList<>();
            exerciseService.create(name, reps, difficulty, weights, type, description, image, comments, 0, 0);
            return "redirect:/exercise-list";
        }
    }

    @GetMapping("/exercise/{id}/details/")
    public String detailsExercise(@PathVariable Long id, Model model) throws InvalidExerciseIdException {
        Exercise exercise = exerciseService.findById(id);
        model.addAttribute("comments",exercise.getComments());
        model.addAttribute("exercise", exercise);
        model.addAttribute("spLinks", spotifyLinkService.findAll());
        model.addAttribute("bodyContent", "details-exercise");
        return "master-template";
    }

    @DeleteMapping("/exercise/{id}/delete/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteExercise(@PathVariable Long id) throws InvalidExerciseIdException {
        this.exerciseService.delete(id);
        return "redirect:/exercise-list";
    }

    @GetMapping("/exercise/{id}/edit/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editFormExercise(@PathVariable Long id, Model model) throws InvalidExerciseIdException {
        Exercise exercise = this.exerciseService.findById(id);
        model.addAttribute("exercise", exercise);
        model.addAttribute("type", Type.values());
        model.addAttribute("spLinks", spotifyLinkService.findAll());
        model.addAttribute("bodyContent", "add-exercises");
        return "master-template";
    }

    @PostMapping("/exercise/{id}/like/")
    public String likeExercise(@PathVariable Long id) throws InvalidExerciseIdException {
        exerciseService.like(id);
        return "redirect:/exercise-list";
    }

    @PostMapping("/exercise/{id}/dislike/")
    public String dislikeExercise(@PathVariable Long id) throws InvalidExerciseIdException {
        exerciseService.dislike(id);
        return "redirect:/exercise-list";
    }
}
