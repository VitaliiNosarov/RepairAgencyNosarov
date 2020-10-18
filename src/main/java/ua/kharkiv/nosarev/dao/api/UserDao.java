package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.exception.RegistrationException;

import java.util.List;

public interface UserDao {

    User getUserByEmail(String userLogin);

    User getUserById(long userId);

    List<User> findUsers(long startPosition, long recordsPerPage);

    List<User> getAllUsersByRole(UserRole role);

    User saveUser(User user);

    long amountOfUsers();
}
