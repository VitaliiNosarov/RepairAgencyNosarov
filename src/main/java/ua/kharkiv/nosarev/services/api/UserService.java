package ua.kharkiv.nosarev.services.api;

import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;

import java.util.List;

public interface UserService {

    public User getUserByEmailPass(String userEmail, String userPass);

    public User getUserById(long id);

    User saveUser(User user);

    User updateUser(User user);

    List<User> getAllUsers();

    List<User> getAllUsersByRole(UserRole role);

    boolean deleteUserById(long userId);

    boolean checkRole(UserRole expectedRole, long userId);
}
