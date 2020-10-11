package ua.kharkiv.nosarev.services;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.UserDao;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.exception.AuthenticationException;
import ua.kharkiv.nosarev.exception.RegistrationException;
import ua.kharkiv.nosarev.exception.ServiceException;
import ua.kharkiv.nosarev.services.api.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static ua.kharkiv.nosarev.services.Validator.*;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByEmailPass(String userEmail, String userPass) throws AuthenticationException{
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
    public User getUserById(long id) {
        if (id > 0) {
            return userDao.getUserById(id);
        } else {
            LOGGER.info("Service exception in get user by Id  " + id);
            throw new ServiceException();
        }
    }

    @Override
    public User saveUser(User user) throws RegistrationException{
        if (!validateUser(user)) {
            LOGGER.info("Wrong registration " + user.getEmail());
            throw new RegistrationException();
        }
        if (userDao.getUserByEmail(user.getEmail()) != null) {
            LOGGER.info("Wrong registration " + user.getEmail());
            throw new RegistrationException();
        }
        return userDao.insertUser(user);
    }

    @Override
    public User updateUser(User user) throws RegistrationException{
        if (!validateUser(user)) {
            LOGGER.info("Can't update account");
            throw new RegistrationException();
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
    public boolean deleteUserById(long userId) {
        if (userId != 0) {
            return userDao.deleteUserById(userId);
        } else {
            LOGGER.info("Service exception in deleteUserById. UserId  = " + userId);
            throw new ServiceException();
        }
    }

    //TODO
//
//    @Override
//    public boolean checkRole(UserRole expectedRole, long userId) {
//        if (userId != 0 && expectedRole != null) {
//            return expectedRole.equals(userDao.getRoleById(userId));
//        } else {
//            LOGGER.info("Service exception in checkRole. Input userId = " + userId + " expectedRole " + expectedRole);
//            throw new ServiceException();
//        }
//    }

    private boolean checkPass(User user, String userPass) {
        if (user != null && userPass != null) {
            return user.getPassword().equals(userPass);
        } else {
            LOGGER.info("Service exception in checkPass. Input user = " + user + " pass " + userPass);
            throw new AuthenticationException();
        }
    }

}
