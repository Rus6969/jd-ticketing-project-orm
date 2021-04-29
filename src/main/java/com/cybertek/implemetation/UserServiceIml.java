package com.cybertek.implemetation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.entity.Project;
import com.cybertek.entity.User;
import com.cybertek.exception.TicketingProjectException;
import com.cybertek.mapper.MapperUtil;
import com.cybertek.mapper.UserMapper;
import com.cybertek.repository.UserRepository;
import com.cybertek.service.ProjectService;
import com.cybertek.service.TaskService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceIml implements UserService {
    // injection or constructor 2 options to inject bean
    @Autowired
    UserRepository userRepository;
    //    @Autowired
//    UserMapper userMapper;
    @Autowired
    MapperUtil mapperUtil;
    @Autowired
    ProjectService projectService;
    @Autowired
    TaskService taskService;
 //   to bring third party class need create bean at configuration
    @Autowired
    private PasswordEncoder passwordEncoder;
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
            return mapperUtil.convert(obj, new UserDTO());
        }).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        User obj = userRepository.findByUserName(username);
        return mapperUtil.convert(obj, new UserDTO());
    }


    @Override
    public void save(UserDTO userDTO) {
        User foundUser = userRepository.findByUserName(userDTO.getUserName());
        //dto.setenabled true is need for confirmation ( lik email sms etc ( set it as true )
        userDTO.setEnabled(true);
        User user = mapperUtil.convert(userDTO, new User());
        user.setPassWord(passwordEncoder.encode(user.getPassWord()));
        userRepository.save(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        //find current user
        User user = userRepository.findByUserName(userDTO.getUserName()); /// here user wirh id
        //convert that user
        User convertedUser = mapperUtil.convert(userDTO, new User());
        // we set it as a true bc we are not using it
        convertedUser.setEnabled(true);
        //password always need to be encoded
        convertedUser.setPassWord(passwordEncoder.encode(convertedUser.getPassWord()));
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
        user.setUserName(user.getUserName() + "-" + user.getId());
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
            return mapperUtil.convert(obj, new UserDTO());
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
