package ua.kharkiv.nosarev.services.api;

import ua.kharkiv.nosarev.entitie.User;

import java.util.List;

public interface UserService {

    public User getUserByEmailPass(String userEmail, String userPass);
    User saveUser(User user);
    List<User> getAllUsers();
    boolean deleteUserByEmail(String email);
}
