package com.oneightwo.accounting4goods.service;

import com.oneightwo.accounting4goods.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAll();

    Role getRoleByString(String role);
}
