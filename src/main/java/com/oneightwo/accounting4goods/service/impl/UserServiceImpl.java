package com.oneightwo.accounting4goods.service.impl;

import com.oneightwo.accounting4goods.model.Role;
import com.oneightwo.accounting4goods.model.User;
import com.oneightwo.accounting4goods.repository.UserRepository;
import com.oneightwo.accounting4goods.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByRole(Role role) {
        return userRepository.getUserByRoleId(role.getId());
    }

    @Override
    public Optional<User> getUserByFullName(String surname, String name, String patronymic) {
        return userRepository.getUserByFullName(surname, name, patronymic);
    }
}
