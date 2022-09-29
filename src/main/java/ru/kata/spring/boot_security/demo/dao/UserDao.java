package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void createUser(User user);
    User getUser(long id);
    User getUserByName(String loginName);

    void editUser(long id, User user);
    void deleteUser(long id);
}
