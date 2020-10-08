package ua.kharkiv.nosarev.services;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.UserDao;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.exception.AuthenticationException;
import ua.kharkiv.nosarev.exception.RegistrationException;
import ua.kharkiv.nosarev.exception.ServiceException;
import ua.kharkiv.nosarev.services.api.UserService;

import java.util.List;

import static ua.kharkiv.nosarev.services.Validator.*;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByEmailPass(String userEmail, String userPass) {
        if (!validateEmail(userEmail) || !validatePassword(userPass)) {
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
    public User getUserById(int id) {
        if (id > 0) {
            return userDao.getUserById(id);
        } else {
            LOGGER.info("Service exception in get user by Id  " + id);
            throw new ServiceException();
        }
    }

    @Override
    public User saveUser(User user) {
        if (!validateUser(user)) {
            LOGGER.info("Wrong registration " + user.getEmail());
            throw new RegistrationException("Wrong user fields");
        }
        if (userDao.getUserByEmail(user.getEmail()) != null) {
            LOGGER.info("Wrong registration " + user.getEmail());
            throw new RegistrationException("Wrong user fields");
        }
        return userDao.insertUser(user);
    }
    @Override
    public User updateUser(User user) {
        if (!validateUser(user)) {
            LOGGER.info("Wrong registration " + user.getEmail());
            throw new RegistrationException("Wrong user fields");
        }
        return userDao.insertUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<User> getAllUsersByRole(UserRole role) {
        if (role != null) {
            return userDao.getAllUsersByRole(role);
        } else {
            LOGGER.info("Service exception in getAllUsersByRole. User Role  = " + role);
            throw new ServiceException();
        }
    }

    @Override
    public boolean deleteUserById(int userId) {
        if (userId != 0) {
            return userDao.deleteUserById(userId);
        } else {
            LOGGER.info("Service exception in deleteUserById. UserId  = " + userId);
            throw new ServiceException();
        }
    }

    @Override
    public boolean checkRole(UserRole expectedRole, int userId) {
        if (userId != 0 && expectedRole != null) {
            return expectedRole.equals(userDao.getRoleById(userId));
        } else {
            LOGGER.info("Service exception in checkRole. Input userId = " + userId + " expectedRole " + expectedRole);
            throw new ServiceException();
        }
    }

    private boolean checkPass(User user, String userPass) {
        if (user != null && userPass != null) {
            return user.getPassword().equals(userPass);
        } else {
            LOGGER.info("Service exception in checkPass. Input user = " + user + " pass " + userPass);
            throw new AuthenticationException();
        }
    }

}
