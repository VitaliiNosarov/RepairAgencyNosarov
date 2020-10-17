package ua.kharkiv.nosarev.service;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.nosarev.dao.api.UserDao;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.exception.AuthenticationException;
import ua.kharkiv.nosarev.exception.RegistrationException;
import ua.kharkiv.nosarev.exception.ServiceException;
import ua.kharkiv.nosarev.service.api.UserService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class UserServiceImplTest {

    private UserService userService;
    private UserDao userDao;
    private Validator validator;
    private User user;
    private User plugUserCorrect;
    private User plugUserWrong;

    @Before
    public void setUp() {
        userDao = mock(UserDao.class);
        validator = mock(Validator.class);
        userService = new UserServiceImpl(userDao, validator);

        user = new User();
        user.setId(1);
        user.setEmail("User@email.com");
        user.setPassword("Password1");

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
        when(validator.validateEmail(user.getEmail())).thenReturn(true);
        when(validator.validatePassword(user.getPassword())).thenReturn(true);
        when(userDao.getUserByEmail(anyString())).thenReturn(plugUserCorrect);
        User newUser = userService.getUserByEmailPass(user.getEmail(), user.getPassword());
        assertEquals(user.getEmail(), newUser.getEmail());
    }

    @Test(expected = AuthenticationException.class)
    public void getUserByEmailPassShouldThrowAuthenticationExceptionWhenPassWrong() {
        when(userDao.getUserByEmail(anyString())).thenReturn(plugUserWrong);
        User newUser = userService.getUserByEmailPass(user.getEmail(), user.getPassword());
        assertEquals(user.getEmail(), newUser.getEmail());
    }

    @Test(expected = AuthenticationException.class)
    public void getUserByEmailPassShouldThrowAuthenticationExceptionWhenEmailPassNotValid() {
        when(validator.validateEmail(user.getEmail())).thenReturn(true);
        when(validator.validatePassword(user.getPassword())).thenReturn(true);
        when(userDao.getUserByEmail(anyString())).thenReturn(plugUserWrong);
        userService.getUserByEmailPass(plugUserWrong.getEmail(), plugUserWrong.getPassword());
    }

    @Test(expected = ServiceException.class)
    public void getUserByIdShouldThrowServiceExceptionWenIdIsWrong() {
        userService.getUserById(0);
    }

    @Test
    public void getUserByIdShouldReturnUser() {
        when(userDao.getUserById(user.getId())).thenReturn(user);
        assertEquals(user, userService.getUserById(user.getId()));
    }

    @Test(expected = RegistrationException.class)
    public void saveUserShouldThrowRegistrationExceptionWhenValidationFalse() {
        when(validator.validateUser(user)).thenReturn(false);
        userService.saveUser(plugUserWrong);
    }

    @Test(expected = RegistrationException.class)
    public void saveUserShouldThrowRegistrationExceptionWhenUserIsAlreadyRegistered() {
        when(validator.validateUser(user)).thenReturn(true);
        when(userDao.getUserByEmail(user.getEmail())).thenReturn(user);
        userService.saveUser(user);
    }

    @Test
    public void saveUserShouldEncodePasswordBeforePassingToDao() {
        when(validator.validateUser(user)).thenReturn(true);
        when(userDao.saveUser(user)).thenReturn(user);
        User newUser = userService.saveUser(user);
        assertEquals(128, newUser.getPassword().length());
    }

    @Test(expected = RegistrationException.class)
    public void updateUserShouldThrowValidationExWhenUserWrong() {
        when(validator.validateUser(user)).thenReturn(false);
        userService.updateUser(plugUserWrong);
    }

    @Test
    public void updateUserShouldEncodePasswordWhenPassIsNew() {
        when(validator.validateUser(user)).thenReturn(true);
        when(userDao.saveUser(user)).thenReturn(plugUserCorrect);
        User newUser = userService.saveUser(user);
        assertNotSame(user.getPassword(), newUser.getPassword());
        assertEquals(128, newUser.getPassword().length());
    }

    @Test
    public void updateUserShouldNoTEncodePasswordWhenPassIsOld() {
        when(validator.validateUser(user)).thenReturn(true);
        when(userDao.getUserById(user.getId())).thenReturn(user);
        when(userDao.saveUser(user)).thenReturn(user);
        User newUser = userService.saveUser(user);
        assertEquals(user.getPassword(), newUser.getPassword());
    }

    @Test
    public void findUsersShouldCalculateCorrectStartPosition() {
        List<User> mockList = mock(List.class);
        when(userDao.findUsers(10, 10)).thenReturn(mockList);
        assertEquals(mockList, userService.findUsers(2, 10));
    }

    @Test
    public void getAllUsersByRoleShouldCallDaoGetAllUsersByRole() {
        userService.getAllUsersByRole(UserRole.MASTER);
        verify(userDao).getAllUsersByRole(UserRole.MASTER);
    }

    @Test
    public void getAmountOfUsersShouldCallDaoAmountOfUsers() {
        userService.getAmountOfUsers();
        verify(userDao).amountOfUsers();
    }
}