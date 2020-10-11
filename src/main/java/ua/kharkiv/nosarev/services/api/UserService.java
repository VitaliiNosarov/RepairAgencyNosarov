package ua.kharkiv.nosarev.services.api;

import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.exception.AuthenticationException;
import ua.kharkiv.nosarev.exception.RegistrationException;

import javax.servlet.Registration;
import java.util.List;

public interface UserService {

    User getUserByEmailPass(String userEmail, String userPass) throws AuthenticationException;

    User saveUser(User user) throws RegistrationException;

    User updateUser(User user) throws RegistrationException;

    User getUserById(long id);

    List<User> getAllUsers();

    List<User> getAllUsersByRole(UserRole role);

    boolean deleteUserById(long userId);

//    boolean checkRole(UserRole expectedRole, long userId);
}
