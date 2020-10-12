package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.exception.RegistrationException;

import java.util.List;

public interface UserDao {

    User getUserByEmail(String userLogin);

    User getUserById(long userId);

    boolean deleteUserById(long userId);

    List<User> findUsers(long startPosition, long recordsPerPage);

    List<User> getAllUsersByRole(UserRole role);

    User insertUser(User user) throws RegistrationException;

    UserRole getRoleById(long userId);

    int amountOfUsers();
}
