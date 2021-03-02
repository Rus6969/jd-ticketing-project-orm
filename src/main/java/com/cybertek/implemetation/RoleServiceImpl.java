package com.cybertek.implemetation;

import com.cybertek.dto.RoleDTO;
import com.cybertek.entity.Role;
import com.cybertek.repository.RoleRepository;
import com.cybertek.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<RoleDTO> listAllRoles() {
       List<Role>list=roleRepository.findAll();

       // convert to DTO and return it (list of roles) Mapper
       return null;

    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }
}
