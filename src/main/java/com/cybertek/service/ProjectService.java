package com.cybertek.service;

import com.cybertek.dto.ProjectDTO;

import java.util.List;


public interface ProjectService {

    ProjectDTO getProjectCode(String code);
    List<ProjectDTO> listAllProject();
    ProjectDTO save(ProjectDTO projectDTO);
    ProjectDTO update(ProjectDTO projectDTO);
    void delete(String code);

}
