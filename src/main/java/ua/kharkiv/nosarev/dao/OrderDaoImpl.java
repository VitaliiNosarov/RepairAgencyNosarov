package ua.kharkiv.nosarev.dao;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.OrderDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.exception.DatabaseException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);
    private DataSource dataSource;

    public OrderDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Order getOrderByUserId(int userId) {
        Order order = null;
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQLConstant.GET_ORDER_BY_USER_ID)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                order = extractOrder(rs);
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
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQLConstant.DELETE_ORDER_BY_ID)) {
            statement.setInt(1, orderId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            LOGGER.error("Can't delete order with id " + orderId + "from database", throwables);
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

    private static Order extractOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        while (rs.next()) {
            order.setId(rs.getInt("id"));
            order.setCustomerId(rs.getInt("b.user_account_id"));
            order.setMasterId(rs.getInt("b.master_account_id"));
            order.setPrice(rs.getBigDecimal("b.price"));
            order.addService(rs.getString("s.name"));
            order.setPaymentId(rs.getInt("b.payment_id"));
            order.setCreatingTime(rs.getTimestamp("b.creating_time"));
            order.setComment(rs.getString("b.customer_comment"));
            order.setStatus(OrderStatus.valueOf(rs.getString("b.order_status")));
        }
        return order;
    }
}
