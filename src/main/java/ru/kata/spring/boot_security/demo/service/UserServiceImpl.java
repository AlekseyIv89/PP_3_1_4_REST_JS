package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;

        //Начальное добавление USER и ADMIN в БД
        //В качестве логина используется e-mail
        if (userDao.getAllUsers().size() < 2) {
            User user = new User("Petr",
                    "Petrov",
                    25,
                    "user@mail.ru",
                    "user",
                    Stream.of(new Role("ROLE_USER")).collect(Collectors.toSet()));

            User admin = new User("Aleksey",
                    "Ivanov",
                    33,
                    "admin@mail.ru",
                    "admin",
                    Stream.of(new Role("ROLE_ADMIN"), new Role("ROLE_USER")).collect(Collectors.toSet()));

            createUser(user);
            createUser(admin);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    @Transactional
    public void editUser(long id, User user) {
        userDao.editUser(id, user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByEmail(username);
    }
}
