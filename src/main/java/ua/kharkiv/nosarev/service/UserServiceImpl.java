package ua.kharkiv.nosarev.service;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import ua.kharkiv.nosarev.dao.api.UserDao;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.exception.AuthenticationException;
import ua.kharkiv.nosarev.exception.RegistrationException;
import ua.kharkiv.nosarev.exception.ServiceException;
import ua.kharkiv.nosarev.service.api.UserService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserServiceImpl implements UserService {


    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private UserDao userDao;
    private Validator validator;

    public UserServiceImpl(UserDao userDao, Validator validator) {
        this.userDao = userDao;
        this.validator = validator;
    }

    @Override
    public User getUserByEmailPass(String userEmail, String userPass){
        if (!validator.validateEmail(userEmail) || !validator.validatePassword(userPass)) {
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
    public User saveUser(User user){
        if (!validator.validateUser(user)) {
            LOGGER.info("Wrong registration " + user.getEmail());
            throw new RegistrationException();
        }
        if (userDao.getUserByEmail(user.getEmail()) != null) {
            LOGGER.info("Wrong registration " + user.getEmail());
            throw new RegistrationException();
        }
        user.setPassword(getSecurePassword(user.getPassword(), getSalt(user.getPassword())));
        return userDao.saveUser(user);
    }

    @Override
    public User updateUser(User user){
        if (!validator.validateUser(user)) {
            LOGGER.info("Can't update account");
            throw new RegistrationException();
        }
        User currentUser = userDao.getUserById(user.getId());
        if (!currentUser.getPassword().equals(user.getPassword())) {
            user.setPassword(getSecurePassword(user.getPassword(), getSalt(user.getPassword())));
        }
        return userDao.saveUser(user);
    }

    @Override
    public List<User> findUsers(long currentPage, long recordsPerPage) {
        long startPosition = currentPage * recordsPerPage - recordsPerPage;
        return userDao.findUsers(startPosition, recordsPerPage);
    }

    @Override
    public List<User> getAllUsersByRole(UserRole role) {
            return userDao.getAllUsersByRole(role);
    }

    @Override
    public long getAmountOfUsers() {
        return userDao.amountOfUsers();
    }

    private boolean checkPass(User user, String userPass) {
        if (user != null && userPass != null) {
            String encodedPass = getSecurePassword(userPass, getSalt(userPass));
            return user.getPassword().equals(encodedPass);
        } else {
            LOGGER.info("Service exception in checkPass. Input user = " + user + " pass " + userPass);
            throw new AuthenticationException();
        }
    }

    private String getSecurePassword(String passwordToHash, String salt) {
        String generatedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.info("Service exception in getSecurePassword");
            throw new ServiceException();
        }
        return generatedPassword;
    }

    private String getSalt(String pass) {
        return Base64.encodeBase64String(pass.getBytes());
    }
}
