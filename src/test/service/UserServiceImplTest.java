package service;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.nosarev.dao.api.UserDao;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.exception.AuthenticationException;
import ua.kharkiv.nosarev.exception.RegistrationException;
import ua.kharkiv.nosarev.exception.ServiceException;
import ua.kharkiv.nosarev.service.UserServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.kharkiv.nosarev.service.Validator.validateUser;


public class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserDao userDao;
    private User user;
    private User plugUserCorrect;
    private User plugUserWrong;

    @Before
    public void setUp() {
        userDao = mock(UserDao.class);
        userService = new UserServiceImpl(userDao);
        user = new User();
        user.setId(1);
        user.setEmail("User@email.com");
        user.setPassword("Password1");
        user.setPhone("0950531870");
        user.setName("User");
        user.setSurName("UserSurname");
        user.setLocale(UserLocale.RU);

        /* Plug user with same password after encoding for check private method checkPass()
         **/
        plugUserCorrect = new User();
        plugUserCorrect.setEmail("User@email.com");
        plugUserCorrect.setPassword("25ef3db0275ac7fc4d0b9a8106748d37dc1ad189fc174fce949451d25a6" +
                "a450a50dc78b56cf9baf3c5807a4e5de1687e01d71714489715d3281c5c16457fa3e1");

        plugUserWrong = new User();
        plugUserWrong.setEmail("email@email.com");
        plugUserWrong.setPassword("WrongPass");
    }

    @Test
    public void getUserByEmailPassShouldReturnCorrectUser() {
        when(userDao.getUserByEmail(anyString())).thenReturn(plugUserCorrect);
        User newUser = userService.getUserByEmailPass(user.getEmail(), user.getPassword());
        assertEquals(user.getEmail(), newUser.getEmail());
    }

    @Test(expected = AuthenticationException.class)
    public void getUserByEmailPassShouldThrowAuthenticationException() {
        when(userDao.getUserByEmail(anyString())).thenReturn(plugUserWrong);
        User newUser = userService.getUserByEmailPass(user.getEmail(), user.getPassword());
        assertEquals(user.getEmail(), newUser.getEmail());
    }

    @Test(expected = ServiceException.class)
    public void getUserByIdShouldThrowServiceException() {
        userService.getUserById(0);
    }

    @Test
    public void getUserByIdShouldReturnUser(){
        when(userDao.getUserById(user.getId())).thenReturn(user);
        assertEquals(user, userService.getUserById(user.getId()));
    }

    @Test(expected = RegistrationException.class)
    public void saveUserShouldThrowRegistrationExceptionWhenValidationFalse() {
        userService.saveUser(plugUserWrong);
    }

    @Test(expected = RegistrationException.class)
    public void saveUserShouldThrowRegistrationExceptionWhenUserIsAlreadyRegistered() {
        when(userDao.getUserByEmail(user.getEmail())).thenReturn(user);
        userService.saveUser(user);
    }

    @Test
    public void saveUserShouldEncodePasswordBeforeSavingToDB(){
        when(userDao.saveUser(user)).thenReturn(user);
        User newUser = userService.saveUser(user);
        assertEquals(128, newUser.getPassword().length());
    }

    @Test(expected = RegistrationException.class)
    public void updateUserShouldThrowWalidationExWhwnUserWrong() {
        userService.updateUser(plugUserWrong);
    }

    @Test
    public void updateUserShouldEncodePasswordWhenPassIsNew() {
        when(userDao.saveUser(user)).thenReturn(user);
        User newUser = userService.saveUser(user);
        assertEquals(128, newUser.getPassword().length());
    }

    @Test
    public void updateUserShouldNoTEncodePasswordWhenPassIsOld() {
        when(userDao.getUserById(user.getId())).thenReturn(user);
        when(userDao.saveUser(user)).thenReturn(user);
        User newUser = userService.saveUser(user);
        assertEquals(user.getPassword(), newUser.getPassword());
    }

//    public User updateUser(User user) throws RegistrationException {
//        if (!validateUser(user)) {
//            LOGGER.info("Can't update account");
//            throw new RegistrationException();
//        }
//        User currentUser = userDao.getUserById(user.getId());
//        if (!currentUser.getPassword().equals(user.getPassword())) {
//            user.setPassword(getSecurePassword(user.getPassword(), getSalt(user.getPassword())));
//        }
//        return userDao.saveUser(user);


    @Test
    public void findUsers() {
    }

    @Test
    public void getAllUsersByRole() {
    }

    @Test
    public void getAmountOfUsers() {
    }
}