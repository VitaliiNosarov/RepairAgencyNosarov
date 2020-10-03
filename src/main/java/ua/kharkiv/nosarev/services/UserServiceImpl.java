package ua.kharkiv.nosarev.services;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.UserDao;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.exception.AuthenticationException;
import ua.kharkiv.nosarev.exception.RegistrationException;
import ua.kharkiv.nosarev.services.api.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByEmailPass(String userEmail, String userPass) {
        if (!Validator.validateEmail(userEmail) || !Validator.validatePassword(userPass)) {
            throw new AuthenticationException();
        }
        User user = userDao.getUserByEmail(userEmail);
        if (user != null && checkPass(user, userPass)) {
            return user;
        } else {
            LOGGER.info("Wrong authorization " + userEmail);
            throw new AuthenticationException();
        }
    }

    @Override
    public String[] getUserFullName(int userId) {
        User user = userDao.getUserById(userId);
        if (user != null) {
            return new String[]{user.getName(), user.getSurName()};
        } else {
            LOGGER.info("Wrong authorization " + userId);
            throw new AuthenticationException();
        }
    }

    @Override
    public User saveUser(User user) {
        if (!Validator.validateUser(user) || userDao.getUserByEmail(user.getEmail()) != null) {
            LOGGER.info("Wrong registration " + user.getEmail());
            throw new RegistrationException();
        }
        return userDao.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public boolean deleteUserById(int userId) {
        return userDao.deleteUserById(userId);
    }

    public boolean checkPass(User user, String userPass) {
        return user.getPassword().equals(userPass);
    }

}
