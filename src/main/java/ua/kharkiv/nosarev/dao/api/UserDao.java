package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;

import java.util.List;

public interface UserDao {

    User getUserByEmail(String userLogin);

    User getUserById(int userId);

    boolean deleteUserById(int userId);

    List<User> getAllUsers();

    List<User> getAllUsersByRole(UserRole role);

    User insertUser(User user);

    User updateUser(User user);

    UserRole getRoleById(int userId);
}
