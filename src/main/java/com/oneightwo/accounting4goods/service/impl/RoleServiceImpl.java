package com.oneightwo.accounting4goods.service.impl;

import com.oneightwo.accounting4goods.model.Role;
import com.oneightwo.accounting4goods.repository.RoleRepository;
import com.oneightwo.accounting4goods.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByString(String role) {
        return roleRepository.getRoleByString(role);
    }
}
