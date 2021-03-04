package com.cybertek.implemetation;

import com.cybertek.dto.RoleDTO;
import com.cybertek.entity.Role;
import com.cybertek.mapper.RoleMapper;
import com.cybertek.repository.RoleRepository;
import com.cybertek.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    // injected this repository to bring data from data base , however we bring it as entity , thats wahy we need to have
    // mapper to converrt it to dto and bring to UI
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    // dto
    public List<RoleDTO> listAllRoles() {
        //entity
        List<Role> list = roleRepository.findAll();

        // convert to DTO and return it (list of roles)  mapp to DTO and return to controller

        return list.stream().map(obj -> {
            return roleMapper.convertToDto(obj);
        }).collect(Collectors.toList());

    }

    @Override
    public RoleDTO findById(Long id) {
     Role role= roleRepository.findById(id).get();
     return  roleMapper.convertToDto(role);

    }
}
