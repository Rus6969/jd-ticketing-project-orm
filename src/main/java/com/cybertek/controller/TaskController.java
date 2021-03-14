package com.cybertek.controller;

import com.cybertek.dto.TaskDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.ProjectService;
import com.cybertek.service.TaskService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;

   @GetMapping("/create")
    public String createTask(Model model){

        model.addAttribute("task",new TaskDTO());
        model.addAttribute("projects",projectService.listAllProjects());
        model.addAttribute("employees",userService.ListAllByRole("employee"));
        model.addAttribute("tasks",taskService.listAllTasks());

        return "task/create";
    }

    @PostMapping("/create")
    public String insertTask(Model model,TaskDTO task){

        taskService.save(task);

        return "redirect:/task/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id){
        taskService.delete(id);
        return "redirect:/task/create";
    }


    @GetMapping("/update/{id}")
    public String editTask(@PathVariable("id")Long id,Model model){
        model.addAttribute("task",taskService.findById(id));
        model.addAttribute("projects",projectService.listAllProjects());
        model.addAttribute("employees",userService.ListAllByRole("employee"));
        model.addAttribute("tasks",taskService.listAllTasks());
     return "task/update";
    }

    @PostMapping("/update/{id}")
    public String updateTask( TaskDTO task,Model model){
        taskService.update(task);
        return "redirect:/task/create";
    }

    @GetMapping("/employee")
    public String edit(Model model){
       List<TaskDTO> tasks = taskService.listAllTaskByStatusIsNot(Status.COMPLETE);

       model.addAttribute("tasks",tasks);
       return "task/employee-tasks";

    }

}