package com.example.fitnessapp.web.controller;

import com.example.fitnessapp.model.exceptions.InvalidCommentIdException;
import com.example.fitnessapp.model.exceptions.InvalidExerciseIdException;
import com.example.fitnessapp.service.CommentService;
import com.example.fitnessapp.service.ExerciseService;
import com.example.fitnessapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("add-comment-form")
    public String addComment(Model model, String user, String content, Long exercise){
        model.addAttribute("bodyContent", "add-comment-form");
        return "master-template";
    }

    @GetMapping("edit-comment/{id}")
    public String editComment(Model model,@PathVariable Long id, String user, String content, Long exercise) throws InvalidCommentIdException {
        model.addAttribute("comment", commentService.findById(id) );
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
        return "redirect:/exercise-list";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id) throws InvalidCommentIdException {
        commentService.delete(id);
        return "redirect:/exercise-list";
    }
}
