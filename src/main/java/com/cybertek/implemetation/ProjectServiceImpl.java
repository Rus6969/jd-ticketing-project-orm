package com.cybertek.implemetation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.entity.Project;
import com.cybertek.entity.User;
import com.cybertek.enums.Status;
import com.cybertek.mapper.ProjectMapper;
import com.cybertek.mapper.UserMapper;
import com.cybertek.repository.ProjectRepository;
import com.cybertek.service.ProjectService;
import com.cybertek.service.TaskService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @Override
    public ProjectDTO getByProjectCode(String code) {
        Project project = projectRepository.findByProjectCode(code);
        return projectMapper.convertToDto(project);
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        List<Project> list = projectRepository.findAll(Sort.by("projectCode"));
        return list.stream().map(obj -> projectMapper.convertToDto(obj)).collect(Collectors.toList());
    }

    @Override
    public Project save(ProjectDTO dto) {
        dto.setProjectStatus(Status.OPEN);
        Project obj = projectMapper.convertToEntity(dto);
        //   obj.setAssignedManager(userMapper.convertToEntity(dto.getAssignedManager()));
        Project project = projectRepository.save(obj);
        return project;
    }

    @Override
    public void update(ProjectDTO dto) {
        Project project = projectRepository.findByProjectCode(dto.getProjectCode());
        Project convertedProject = projectMapper.convertToEntity(dto);
        // if do not set id here it will create new project which we do not want we want modify project
        convertedProject.setId(project.getId());
        // we need get status of a project to assign it to updated one in another case we will lose value
        convertedProject.setProjectStatus(project.getProjectStatus());
        projectRepository.save(convertedProject);
    }

    @Override
    public void delete(String code) {
        Project project = projectRepository.findByProjectCode(code);
        project.setIsDeleted(true);
        // in a line 69 since we set project as deleted true which means we can not create any other project with same projectCode
        // thats why we assign same project code with unique id , that's how we can use same project code name
        project.setProjectCode(project.getProjectCode() + "-" + project.getId());
        // we added that line of code to avoid collapse  when we delete project we delete task by  projectId
        taskService.deleteByProject(projectMapper.convertToDto(project));
        projectRepository.save(project);

    }

    @Override
    public void complete(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        project.setProjectStatus(Status.COMPLETE);
        projectRepository.save(project);

    }

    @Override
    public List<ProjectDTO> listAllProjectDetails() {
        // this class holds all credentials
        /*
        The SecurityContext and SecurityContextHolder are two fundamental classes of Spring Security.
        The SecurityContext is used to store the details of the currently authenticated user, also known as a principle.
        So, if you have to get the username or any other user details, you need to get this SecurityContext first.
        https://www.baeldung.com/get-user-in-spring-security
         */
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO currentUserDTO = userService.findByUserName(username);
        User user = userMapper.convertToEntity(currentUserDTO);
        List<Project> list = projectRepository.findAllByAssignedManager(user);

        return list.stream().map(project -> {
            ProjectDTO obj = projectMapper.convertToDto(project);
            obj.setUnfinishedTaskCounts(taskService.totalNonCompletedTasks(project.getProjectCode()));
            obj.setCompleteTaskCounts(taskService.totalCompletedTasks(project.getProjectCode()));
            return obj;
        }).collect(Collectors.toList());


    }

    @Override
    public List<ProjectDTO> readAllByAssignedManager(User user) {
        List<Project> list = projectRepository.findAllByAssignedManager(user);

        return list.stream().map(projectMapper::convertToDto).collect(Collectors.toList());

    }
}