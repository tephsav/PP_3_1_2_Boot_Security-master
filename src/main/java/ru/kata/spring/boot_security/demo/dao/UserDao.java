package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void addUser(User user);

    void deleteUserById(Long id);

    void updateUser(User user);

    User getUserById(Long id);

    User getUserByName(String name);
}
