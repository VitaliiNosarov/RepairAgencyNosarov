package ua.kharkiv.nosarev.dao;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.UserDao;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.exception.AuthorizationException;
import ua.kharkiv.nosarev.exception.DatabaseException;
import ua.kharkiv.nosarev.exception.RegistrationException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private DataSource dataSource;

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        User user = null;
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQLConstant.GET_USER_BY_EMAIL_PASS)) {
            statement.setString(1, userEmail);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    user = extractUser(rs);
                }
            }
        } catch (SQLException throwables) {
            LOGGER.error("SQL Exception in getUserByLoginPass " + throwables);
            throw new DatabaseException();
        }
        return user;
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        boolean result = false;
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQLConstant.DELETE_USER_BY_EMAIL)) {
            statement.setString(1, email);
            result = statement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            LOGGER.error("Can't delete user with email "+ email +"from database", throwables);
            throw new DatabaseException();
        }
        return result;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(SQLConstant.GET_ALL_USERS);
            while(set.next()){
                list.add(extractUser(set));
            }
        } catch (SQLException throwables) {
            LOGGER.error("Can't get all users from database", throwables);
            throw new DatabaseException();
        }
        return list;
    }

    @Override
    public User insertUser(User user) {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQLConstant.INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            if (user != null) {
                setUserToPreparedStatement(user, statement);
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }
        } catch (SQLException throwables) {
            LOGGER.error("Can't insert user with email = " + user.getEmail() + " to database");
            throw new RegistrationException();
        }
        return user;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setSurName(rs.getString("surname"));
        user.setPhone(rs.getString("phone"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setBalance(rs.getBigDecimal("balance"));
        user.setLocale(UserLocale.valueOf(rs.getString("locale")));
        user.setRole(UserRole.valueOf(rs.getString("role")));
        return user;
    }

    private void setUserToPreparedStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getName());
        statement.setString(2, user.getSurName());
        statement.setString(3, user.getPhone());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getPassword());
        statement.setBigDecimal(6, user.getBalance());
        statement.setString(7, String.valueOf(user.getLocale()));
        if (user.getRole() == null) {
            statement.setString(8, String.valueOf(UserRole.CUSTOMER));
        } else {
            statement.setString(8, String.valueOf(user.getRole()));
        }
    }
}