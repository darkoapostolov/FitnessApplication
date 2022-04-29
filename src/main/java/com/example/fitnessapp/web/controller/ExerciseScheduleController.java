package com.example.fitnessapp.web.controller;

import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.ExerciseSchedule;
import com.example.fitnessapp.model.enumerations.Type;
import com.example.fitnessapp.model.enumerations.Weights;
import com.example.fitnessapp.model.exceptions.InvalidExerciseIdException;
import com.example.fitnessapp.model.exceptions.InvalidExerciseScheduleIdException;
import com.example.fitnessapp.service.ExerciseScheduleService;
import com.example.fitnessapp.service.ExerciseService;
import com.example.fitnessapp.service.UserService;
import com.example.fitnessapp.service.impl.SpotifyLinkServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/schedules")
public class ExerciseScheduleController {
    private final ExerciseService exerciseService;
    private final ExerciseScheduleService exerciseScheduleService;
    private final SpotifyLinkServiceImpl service;
    private final UserService userService;

    public ExerciseScheduleController(ExerciseService exerciseService, ExerciseScheduleService exerciseScheduleService, SpotifyLinkServiceImpl service, UserService userService) {
        this.exerciseService = exerciseService;
        this.exerciseScheduleService = exerciseScheduleService;
        this.service = service;
        this.userService = userService;
    }


    @GetMapping
    public String getSchedulesPage(@RequestParam(required = false) String error, Model model, HttpServletRequest request) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<ExerciseSchedule> scheduleList = new ArrayList<>();
        List<ExerciseSchedule> schedules = this.exerciseScheduleService.listAll();
        for (int i=0;i<schedules.size();i++){
            if (schedules.get(i).getUsername().equals("Darko")){
                scheduleList.add(schedules.get(i));
            }
        }
        model.addAttribute("schedules", scheduleList);
        model.addAttribute("spLinks", service.findAll());
        model.addAttribute("bodyContent", "schedule-list");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) throws InvalidExerciseScheduleIdException {
        this.exerciseScheduleService.delete(id);
        return "redirect:/schedules";
    }

    @DeleteMapping("/user-delete/{id}")
    public String userDeleteSchedule(@PathVariable Long id) throws InvalidExerciseScheduleIdException {
        this.exerciseScheduleService.delete(id);
        return "redirect:/schedules";
    }

    @GetMapping("/edit-schedule/{id}")
    public String editSchedulePage(@PathVariable Long id, Model model) throws InvalidExerciseScheduleIdException {
            ExerciseSchedule schedule = this.exerciseScheduleService.findById(id);
            model.addAttribute("schedule", schedule);
            model.addAttribute("types", Type.values());
        model.addAttribute("spLinks", service.findAll());
            model.addAttribute("bodyContent", "add-exercise-schedule");
            return "master-template";
    }

    @GetMapping("/user-edit-schedule/{id}")
    public String userEditSchedulePage(@PathVariable Long id, Model model) throws InvalidExerciseScheduleIdException {
        ExerciseSchedule schedule = this.exerciseScheduleService.findById(id);
        model.addAttribute("schedule", schedule);
        model.addAttribute("types", Type.values());
        model.addAttribute("spLinks", service.findAll());
        model.addAttribute("bodyContent", "add-exercise-schedule");
        return "master-template";
    }

    @GetMapping("/schedule/{id}/exercise-list")
    public String listExercises(@PathVariable Long id, Model model, HttpServletRequest request) throws InvalidExerciseIdException, InvalidExerciseScheduleIdException {
        List<Exercise> exercises = exerciseScheduleService.listExercises(id);
        ExerciseSchedule exerciseSchedule = exerciseScheduleService.findById(id);
        model.addAttribute("id",id);
        request.getSession().setAttribute("id",id);
        model.addAttribute("user", exerciseSchedule.getUsername());
        model.addAttribute("spotifyLinks", service.findAll());
        model.addAttribute("exercises", exercises);
        model.addAttribute("spLinks", service.findAll());
        model.addAttribute("bodyContent", "exercise-list");
        return "master-template";
    }

    @GetMapping("/add-schedule")
    public String addSchedulePage(Model model) {
       List<Exercise> exercises = this.exerciseService.findAll();
       model.addAttribute("exercises", exercises);
        model.addAttribute("bool", Weights.values());
       model.addAttribute("types", Type.values());
        model.addAttribute("spLinks", service.findAll());
       model.addAttribute("bodyContent", "add-exercise-schedule");
       return "master-template";
    }

    @PostMapping("/add")
    public String saveSchedule(
            @RequestParam(required = false)
            String name,
            String difficulty,
            Weights weights,
            String image,
            Type type,
            Long id,
            HttpServletRequest request
    ) throws InvalidExerciseScheduleIdException {
        List<Exercise> exercises;
        if (id != null) {

            exercises = exerciseScheduleService.findById(id).getExercises();
            this.exerciseScheduleService.edit(id, name ,difficulty, weights, image, exercises, type);
        } else {
            if (request.getRemoteUser()!=null) {
                String username = request.getRemoteUser();
                exercises = new ArrayList<>();
                ExerciseSchedule exerciseSchedule = this.exerciseScheduleService.create(username, name, difficulty, weights, image, exercises, type);
                userService.addExSchedule(exerciseSchedule,username);
            }else {
                exercises = new ArrayList<>();
                this.exerciseScheduleService.create(null, name, difficulty, weights, image, exercises, type);
            }
        }
        return "redirect:/schedules";
    }

    @GetMapping("/schedule/{id}/add-exercise")
    public String addExerciseToSchedule(HttpServletRequest req, @PathVariable Long id, Model model) throws InvalidExerciseScheduleIdException {
        boolean flag=false;
        List<Exercise> exercises = this.exerciseService.findAll();
        List<Exercise> exercises1 = exerciseScheduleService.findById(id).getExercises();
        List<Exercise> exercises2 = new ArrayList<>();
            for (Exercise e: exercises) {
                for (Exercise e1: exercises1) {
                    if (e == e1) {
                        flag=true;
                    }
                }
                if (flag==false){
                    exercises2.add(e);
                }
                flag=false;
            }
        req.getSession().setAttribute("id",id);
        model.addAttribute("schedule",exerciseScheduleService.findById(id));
        model.addAttribute("exercises", exercises2);
        model.addAttribute("spLinks", service.findAll());
        model.addAttribute("bodyContent", "add-exercise-to-schedule");
        return "master-template";
    }

    @PostMapping("/addToSchedule")
    public String addToSchedule(@RequestParam List<Long> exId, HttpServletRequest request) throws InvalidExerciseIdException, InvalidExerciseScheduleIdException {
        Long id = Long.parseLong(String.valueOf(request.getSession().getAttribute("id")));
        for (int i=0;i<exId.size();i++) {
            this.exerciseScheduleService.addExercise(id, exId.get(i));
        }
        return "redirect:/schedules/userSchedules";
    }

    @GetMapping("/{ids}/removeFromSchedule")
    public String removeFromSchedule(@PathVariable Long ids, HttpServletRequest request) throws InvalidExerciseIdException, InvalidExerciseScheduleIdException {
        Long id = Long.parseLong(String.valueOf(request.getSession().getAttribute("id")));
            this.exerciseScheduleService.removeExercise(id, ids);
        return "redirect:/schedules/userSchedules";
    }

    @GetMapping("/userSchedules")
    public String userSchedules(Model model, HttpServletRequest request) throws InvalidExerciseScheduleIdException {
        List<ExerciseSchedule> exerciseSchedules = new ArrayList<>();
        List<ExerciseSchedule> exerciseSchedules1 = exerciseScheduleService.listAll();
        for (int i=0;i<exerciseSchedules1.size();i++){
            exerciseSchedules = exerciseScheduleService.ListByUser(request.getRemoteUser(),exerciseSchedules1.get(i).getId());
        }
        model.addAttribute("schedules",exerciseSchedules);
        model.addAttribute("bodyContent", "user-schedules");
        return "master-template";
    }
}
