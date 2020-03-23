package com.oneightwo.accounting4goods.service;

import com.oneightwo.accounting4goods.model.Role;
import com.oneightwo.accounting4goods.model.User;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getByUsername(String username);

    List<User> getAll();

    void delete(User user);

    void save(User user);

    void update(User user);

    Optional<User> getUserByRole(Role role);

    Optional<User> getUserByFullName(String surname, String name, String patronymic);
}
