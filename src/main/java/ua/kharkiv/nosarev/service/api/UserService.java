package ua.kharkiv.nosarev.service.api;

import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.exception.AuthenticationException;

import java.util.List;

public interface UserService {

    User getUserByEmailPass(String userEmail, String userPass);

    User saveUser(User user);

    User updateUser(User user);

    User getUserById(long id);

    List<User> findUsers(long currentPage, long recordsPerPage);

    List<User> getAllUsersByRole(UserRole role);

    long getAmountOfUsers();

}
