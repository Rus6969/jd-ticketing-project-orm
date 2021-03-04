package com.cybertek.implemetation;

import com.cybertek.dto.UserDTO;
import com.cybertek.entity.User;
import com.cybertek.mapper.UserMapper;
import com.cybertek.repository.UserRepository;
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
//   UserRepository userRepository;
//    UserMapper userMapper;
//
//    public UserServiceIml(UserRepository userRepository, UserMapper userMapper) {
//        this.userRepository = userRepository;
//        this.userMapper = userMapper;
//    }

    @Override
    public List<UserDTO> listAllUsers() {
       List<User> listUsers =userRepository.findAll(Sort.by("firstName"));

        return listUsers.stream().map(obj -> {return userMapper.convertToDto(obj);}).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        return null;
    }


    @Override
    public void save(UserDTO userDTO) {
     User obj= userMapper.convertToEntity(userDTO);
     userRepository.save(obj);

    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return null;
    }

    @Override
    public void delete(String username) {

    }
}
