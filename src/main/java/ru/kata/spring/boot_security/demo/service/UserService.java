package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    void createUser(User user);
    User getUser(long id);
    User getUserByEmail(String email);
    void editUser(long id, User user);
    void deleteUser(long id);
}
