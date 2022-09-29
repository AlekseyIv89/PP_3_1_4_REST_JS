package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext(unitName = "entityManagerFactory")
    public EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(long id) {
//        TypedQuery<User> typedQuery = entityManager.createQuery("select u from User u where u.id = :id", User.class)
//                .setParameter("id", id);
//        //т.к. при getSingleResult() может быть NoResultException
//        return typedQuery.getResultList().stream().findAny().orElse(null);
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String loginName) {
        User user = entityManager.createQuery("select u from User u where u.userLogin = :login", User.class)
                .setParameter("login", loginName).getSingleResult();

        System.out.println(user);
        return user;
    }

    @Override
    public void editUser(long id, User user) {
        User editedUser = getUser(id);
        editedUser.setUserLogin(user.getUserLogin());
        editedUser.setEmail(user.getEmail());
        editedUser.setUserPassword(user.getUserPassword());
        entityManager.merge(editedUser);
    }

    @Override
    public void deleteUser(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}
