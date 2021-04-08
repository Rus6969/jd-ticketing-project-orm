package com.cybertek.implemetation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.entity.Project;
import com.cybertek.entity.User;
import com.cybertek.exception.TicketingProjectException;
import com.cybertek.mapper.UserMapper;
import com.cybertek.repository.UserRepository;
import com.cybertek.service.ProjectService;
import com.cybertek.service.TaskService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceIml implements UserService {
    // injection or constructor 2 options to inject bean
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ProjectService projectService;
    @Autowired
    TaskService taskService;
//   UserRepository userRepository;
//    UserMapper userMapper;
//
//    public UserServiceIml(UserRepository userRepository, UserMapper userMapper) {
//        this.userRepository = userRepository;
//        this.userMapper = userMapper;
//    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> listUsers = userRepository.findAll(Sort.by("firstName"));

        return listUsers.stream().map(obj -> {
            return userMapper.convertToDto(obj);
        }).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        User obj = userRepository.findByUserName(username);
        return userMapper.convertToDto(obj);
    }


    @Override
    public void save(UserDTO userDTO) {
        User user = userMapper.convertToEntity(userDTO);
        userRepository.save(user);

    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        //find current user
        User user = userRepository.findByUserName(userDTO.getUserName()); /// here user wirh id
        //convert that user
        User convertedUser = userMapper.convertToEntity(userDTO);
        //(problem is without id ) thats why we assign id from entity line 54
        convertedUser.setId(user.getId());
        //save updated user in DB
        userRepository.save(convertedUser);

        return null;
    }

    @Override
    public void delete(String username) throws TicketingProjectException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new TicketingProjectException("User does not exists ");

        }
        if (!checkIfUserCanBeDeleted(user)) {
            throw new TicketingProjectException("User can not be deleted it uses by project or task  ");
        }
        user.setUserName(user.getUserName()+"-"+user.getId());
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    //hardDeleted
    @Override
    public void deleteByUsername(String name) {
        userRepository.deleteByUserName(name);
    }

    @Override
    public List<UserDTO> ListAllByRole(String role) {
        List<User> users = userRepository.findAllByRoleDescriptionIgnoreCase(role);
        return users.stream().map(obj -> {
            return userMapper.convertToDto(obj);
        }).collect(Collectors.toList());
    }

    @Override
    public Boolean checkIfUserCanBeDeleted(User user) {
        switch (user.getRole().getDescription()) {
            case "Manager":
                List<ProjectDTO> projectDTOList = projectService.readAllByAssignedManager(user);
                return projectDTOList.size() == 0;

            case "Employee":
                List<TaskDTO> taskList = taskService.readAllByEmployee(user);
                return taskList.size() == 0;
            default:
                return true;
        }

    }
}
