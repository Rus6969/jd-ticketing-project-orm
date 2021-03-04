package com.cybertek.implemetation;

import com.cybertek.dto.UserDTO;
import com.cybertek.entity.User;
import com.cybertek.repository.UserRepository;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceIml implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDTO> listAllUsers() {
       List<User> listUsers =userRepository.findAll(Sort.by("firstName"));

        return null;
    }

    @Override
    public UserDTO findByUserName(String username) {
        return null;
    }

    @Override
    public void save(UserDTO userDTO) {

    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return null;
    }

    @Override
    public void delete(String username) {

    }
}
