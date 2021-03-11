package com.cybertek.implemetation;

import com.cybertek.dto.TaskDTO;
import com.cybertek.entity.Project;
import com.cybertek.entity.Task;
import com.cybertek.mapper.TaskMapper;
import com.cybertek.mapper.UserMapper;
import com.cybertek.repository.TaskRepository;
import com.cybertek.repository.UserRepository;
import com.cybertek.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    TaskRepository taskRepository;


    @Override
    public TaskDTO findById(Long id) {
        return null;
    }

    @Override
    public List<TaskDTO> listAllTasks() {

        List<Task> list = taskRepository.findAll();
        return list.stream().map(obj->{return taskMapper.convertToDto(obj);}).collect(Collectors.toList());
    }

    @Override
    public Task save(TaskDTO dto) {
        Task task= taskMapper.convertToEntity(dto);
      return   taskRepository.save(task);

    }

    @Override
    public void update(TaskDTO dto) {

    }

    @Override
    public void delete(Long id) {

    }
}
