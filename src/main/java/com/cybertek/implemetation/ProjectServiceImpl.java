package com.cybertek.implemetation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService {

    @Override
    public ProjectDTO getProjectCode(String code) {
        return null;
    }

    @Override
    public List<ProjectDTO> listAllProject() {
        return null;
    }

    @Override
    public ProjectDTO save(ProjectDTO projectDTO) {
        return null;
    }

    @Override
    public ProjectDTO update(ProjectDTO projectDTO) {
        return null;
    }

    @Override
    public void delete(String code) {

    }
}
