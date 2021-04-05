package com.cybertek.service;
import com.cybertek.dto.UserDTO;
import com.cybertek.entity.User;
import com.cybertek.exception.TicketingProjectException;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username);
    void save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    void delete( String username) throws TicketingProjectException;

    void deleteByUsername(String name);
    List<UserDTO>ListAllByRole(String role);

    Boolean checkIfUserCanBeDeleted(User user);
}
