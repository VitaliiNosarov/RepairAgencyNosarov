package ua.kharkiv.nosarev.dao;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.nosarev.dao.api.UserDao;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class UserDaoImplTest {

    private DataSource dataSource;
    private UserDao userDao;
    private User user;

    @Before
    public void setUp() throws Exception {
        dataSource = DBCPDataSource.getDataSource();
        userDao = new UserDaoImpl(dataSource);
        user = new User();
        user.setId(1);
        user.setEmail("example@gmail.com1");
    }

    @Test
    public void getUserByEmailShouldReturnUserFromDB() {
        User newUser = userDao.getUserByEmail(user.getEmail());
        assertEquals(user.getEmail(), newUser.getEmail());
    }

    @Test
    public void getUserByIdReturnUserFromDB() {
        User newUser = userDao.getUserById(user.getId());
        assertEquals(user.getEmail(), newUser.getEmail());
    }

    @Test
    public void findUsersShouldReturnTwoUsersFromBeginInDB() {
        List<User> usersList = userDao.findUsers(0,2);
        assertEquals(2, usersList.size());
        assertEquals(user.getEmail(), usersList.get(0).getEmail());
    }

    @Test
    public void getAllUsersByRoleShouldReturnListWithOneCustomer() {
        assertEquals(1,userDao.getAllUsersByRole(UserRole.CUSTOMER).size());
    }

    @Test
    public void amountOfUsersShouldReturnCorrectAmountFromDB() {
        assertEquals(3,userDao.amountOfUsers());
    }
}



