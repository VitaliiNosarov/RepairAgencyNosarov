package ua.kharkiv.nosarev.services;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.UserDao;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.exception.AuthorizationException;
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
            throw new AuthorizationException();
        }
        User user = userDao.getUserByEmail(userEmail);
        if (user!=null&&checkPass(user, userPass)) {
            return user;
        } else {
            LOGGER.info("Wrong authorization " + userEmail);
            throw new AuthorizationException();
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
    public boolean deleteUserByEmail(String email) {
        if (!Validator.validateEmail(email)) {
            return false;
        }
        return userDao.deleteUserByEmail(email);
    }

    public boolean checkPass(User user, String userPass) {
        return user.getPassword().equals(userPass);
    }

}
