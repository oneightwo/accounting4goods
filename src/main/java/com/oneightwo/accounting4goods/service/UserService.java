package com.oneightwo.accounting4goods.service;

import com.oneightwo.accounting4goods.model.Role;
import com.oneightwo.accounting4goods.model.User;
import com.oneightwo.accounting4goods.service.basic.BasicService;

import java.util.Optional;

public interface UserService extends BasicService<User> {

    Optional<User> getByUsername(String username);

    Optional<User> getByUsernameAndPassword(String username, String password);

    Optional<User> getUserByRole(Role role);

    Optional<User> getUserByFullName(String surname, String name, String patronymic);
}
