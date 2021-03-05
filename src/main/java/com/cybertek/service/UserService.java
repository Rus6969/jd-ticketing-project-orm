package com.cybertek.service;
import com.cybertek.dto.UserDTO;
import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username);
    void save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    void delete( String username);

    void deleteByUsername(String name);
}
