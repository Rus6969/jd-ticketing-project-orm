package com.cybertek.implemetation;

import com.cybertek.dto.RoleDTO;
import com.cybertek.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<RoleDTO> listAllRoles() {
        return null;
    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }
}