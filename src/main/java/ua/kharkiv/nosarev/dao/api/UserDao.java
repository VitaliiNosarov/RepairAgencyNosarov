package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.User;

import java.util.List;

public interface UserDao {

    User getUserByEmail(String userLogin);

    boolean deleteUserByEmail(String email);

    List<User> getAllUsers();

    User insertUser(User user);
}
