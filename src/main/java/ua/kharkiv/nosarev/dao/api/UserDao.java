package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.User;

import java.util.List;

public interface UserDao {

    User getUserByEmail(String userLogin);

    User getUserById(int userId);

    boolean deleteUserById(int userId);

    List<User> getAllUsers();

    User saveUser(User user);
}
