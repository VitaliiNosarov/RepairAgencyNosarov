package ua.kharkiv.nosarev.dao;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.UserDao;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstant.GET_USER_BY_EMAIL_PASS)) {
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
    public User getUserById(long userId) {
        User user = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstant.GET_USER_BY_ID)) {
            statement.setLong(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    user = extractUser(rs);
                }
            }
        } catch (SQLException throwables) {
            LOGGER.error("SQL Exception in getUserById " + throwables);
            throw new DatabaseException();
        }
        return user;
    }

    @Override
    public boolean deleteUserById(long userId) {
        boolean result = false;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstant.DELETE_USER_BY_EMAIL)) {
            statement.setLong(1, userId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            LOGGER.error("Can't delete user with email " + userId + "from database", throwables);
            throw new DatabaseException();
        }
        return result;
    }

    @Override
    public List<User> findUsers(long startPosition, long recordsPerPage) {
        List<User> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstant.FIND_USERS)) {
            statement.setLong(1, startPosition);
            statement.setLong(2, recordsPerPage);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                list.add(extractUser(set));
            }
        } catch (SQLException throwables) {
            LOGGER.error("Can't get users list from database", throwables);
            throw new DatabaseException();
        }
        return list;
    }

    @Override
    public List<User> getAllUsersByRole(UserRole role) {
        List<User> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstant.GET_ALL_USERS_BY_ROLE)) {
            statement.setString(1, role.toString());
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                list.add(extractUser(set));
            }
        } catch (SQLException throwables) {
            LOGGER.error("Can't get all users by role from database", throwables);
            throw new DatabaseException();
        }
        return list;
    }

    @Override
    public User insertUser(User user) throws RegistrationException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstant.SAVE_USER, Statement.RETURN_GENERATED_KEYS)) {
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

    @Override
    public UserRole getRoleById(long userId) {
        UserRole role = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstant.GET_ROLE_BY_USER_ID)) {
            statement.setLong(1, userId);
            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                    role = UserRole.valueOf(set.getString("role"));
                }
            }
        } catch (SQLException throwables) {
            LOGGER.error("Can't get user role by id " + userId);
            throw new DatabaseException();
        }
        return role;
    }

    @Override
    public int amountOfUsers() {
        int amount = 0;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery((SQLConstant.GET_AMOUNT_OF_USERS))) {
                if (rs.next()) {
                    amount = rs.getInt("count");
                }
            }
        } catch (SQLException ex) {
            LOGGER.error("SQL Exception in amountOfUsers " + ex);
            throw new DatabaseException();
        }
        return amount;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setSurName(rs.getString("surname"));
        user.setRole(UserRole.valueOf(rs.getString("role")));
        user.setPhone(rs.getString("phone"));
        user.setLocale(UserLocale.valueOf(rs.getString("locale")));
        user.setBalance(rs.getBigDecimal("balance"));
        return user;
    }

    private void setUserToPreparedStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setLong(1, user.getId());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getName());
        statement.setString(5, user.getSurName());
        statement.setString(6, user.getRole().toString());
        statement.setString(7, user.getPhone());
        statement.setString(8, user.getLocale().toString());
        statement.setBigDecimal(9, user.getBalance());
    }
}