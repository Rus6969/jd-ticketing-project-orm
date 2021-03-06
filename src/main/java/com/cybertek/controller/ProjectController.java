package com.cybertek.controller;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.ProjectService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;


    @GetMapping("/create")
    public String createProject(Model model) {

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("managers", userService.ListAllByRole("manager"));

        return "/project/create";
    }

    @PostMapping("/create")
    public String insertProject(ProjectDTO project) {
        projectService.save(project);
        project.setProjectStatus(Status.OPEN);
        return "redirect:/project/create";

    }

    @GetMapping("/delete/{projectcode}")
    public String deleteProject(@PathVariable("projectcode") String projectcode) {
        projectService.delete(projectcode);
        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectcode}")
    public String completeProject(@PathVariable("projectcode") String projectcode) {
        projectService.complete(projectcode);
        return "redirect:/project/create";
    }

//    @GetMapping("/update/{projectcode}")
//    public String editProject(@PathVariable("projectcode") String projectcode, Model model) {
//
//        model.addAttribute("project", projectService.findById(projectcode));
//        model.addAttribute("projects", projectService.findAll());
//        model.addAttribute("managers", userService.findManagers());
//
//        return "/project/update";
//    }
//
//    @PostMapping("/update/{projectcode}")
//    public String updateProject(@PathVariable("projectcode") String projectcode, ProjectDTO project, Model model) {
//
//        projectService.update(project);
//
//        return "redirect:/project/create";
//    }

//
//    @GetMapping("/manager/complete")
//    public String getProjectByManager(Model model) {
//
//        UserDTO manager = userService.findById("john@cybertek.com");
//
//        List<ProjectDTO> projects = getCountedListOfProjectDTO(manager);
//
//        model.addAttribute("projects", projects);
//
//
//        return "/manager/project-status";
//    }
//
//
//    // curly braces means that we need  return values, first we need to bring all task list( x its eachproject )
//    List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {
//
//        List<ProjectDTO> list = projectService
//                .findAll()
//                .stream()
//                .filter(x -> x.getAssignedManager().equals(manager))
//                .map(x -> {
//
//                    List<TaskDTO> taskList = taskService.findTaskByManager(manager);
//                    int completeCount = (int) taskList.stream().filter(t -> t.getProject().equals(x) && t.getTaskStatus() == Status.COMPLETE).count();
//                    int inCompleteCount = (int) taskList.stream().filter(t -> t.getProject().equals(x) && t.getTaskStatus() != Status.COMPLETE).count();
//
//                    x.setCompleteTaskCounts(completeCount);
//                    x.setUnfinishedTaskCounts(inCompleteCount);
//
//                    return x;
//
//                }).collect(Collectors.toList());
//
//        return list;
//
//
//    }
}


