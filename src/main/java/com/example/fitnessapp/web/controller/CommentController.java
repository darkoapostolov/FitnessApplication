package com.example.fitnessapp.web.controller;

import com.example.fitnessapp.model.Comment;
import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.exceptions.InvalidCommentIdException;
import com.example.fitnessapp.model.exceptions.InvalidExerciseIdException;
import com.example.fitnessapp.service.CommentService;
import com.example.fitnessapp.service.ExerciseService;
import com.example.fitnessapp.service.UserService;
import org.h2.engine.Database;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final ExerciseService exerciseService;
    private final UserService userService;

    public CommentController(CommentService commentService, ExerciseService exerciseService, UserService userService) {
        this.commentService = commentService;
        this.exerciseService = exerciseService;
        this.userService = userService;
    }

    @GetMapping("add-comment-form/{exercise}")
    public String addComment(@PathVariable Long exercise, Model model) throws InvalidExerciseIdException {
        model.addAttribute("exerciseId", exercise);
        model.addAttribute("bodyContent", "add-comment-form");
        return "master-template";
    }

    @GetMapping("/edit-comment/{id}/{exercise}")
    public String editComment(Model model,@PathVariable Long id, @PathVariable Long exercise) throws InvalidCommentIdException {
        model.addAttribute("comment", commentService.findById(id) );
        model.addAttribute("exerciseId", exercise);
        model.addAttribute("bodyContent", "add-comment-form");
        return "master-template";
    }

    @PostMapping("add-edit")
    public String addEdit(@RequestParam(required = false)Long id, String user, String content, Long exercise) throws InvalidCommentIdException, InvalidExerciseIdException {
        if (id!=null){
            commentService.edit(id, userService.findByUsername(user), content, exerciseService.findById(exercise));
        }else {
            commentService.create(userService.findByUsername(user), content, exerciseService.findById(exercise));
        }
        String redirect = "redirect:/exercise-list/exercise/"+exercise+"/details/";
        return redirect;
    }

    @DeleteMapping("delete/{id}/{exercise}")
    public String delete(@PathVariable Long id, @PathVariable Long exercise) throws InvalidCommentIdException, InvalidExerciseIdException {
        Exercise exercise1 = exerciseService.findById(exercise);
        Comment comment = commentService.findById(id);
        exercise1.getComments().remove(comment);
        exerciseService.update(exercise1);
        commentService.delete(id);
        return "redirect:/exercise-list";
    }
}
