package com.oneightwo.accounting4goods.service;

import com.oneightwo.accounting4goods.model.Role;
import com.oneightwo.accounting4goods.service.basic.BasicService;

import java.util.List;
import java.util.Optional;

public interface RoleService extends BasicService<Role> {

    Role getRoleByString(String role);
}
