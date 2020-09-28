package ua.kharkiv.nosarev.dao;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.OrderDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.exception.DatabaseException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);
    private DataSource dataSource;

    OrderDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQLConstant.GET_ORDER_BY_ID)) {
            statement.setInt(1, orderId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    order = extractOrder(rs);
                }
            }
        } catch (SQLException throwables) {
            LOGGER.error("SQL Exception in getOrderById " + throwables);
            throw new DatabaseException();
        }
        return order;
    }

    @Override
    public boolean deleteOrderById(int orderId) {
        boolean result = false;
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQLConstant.DELETE_ORDER_BY_ID)) {
            statement.setInt(1, orderId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            LOGGER.error("Can't delete order with id "+ orderId +"from database", throwables);
            throw new DatabaseException();
        }
        return result;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public Order insertOrder(Order order) {
        return null;
    }

    private Order extractOrder(ResultSet rs) throws SQLException {
        return null;
    }
}
