package ua.kharkiv.nosarev.dao;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.OrderDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.exception.DatabaseException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);
    private DataSource dataSource;

    public OrderDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQLConstant.GET_ORDER_BY_ID)) {
            statement.setInt(1, orderId);
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
        List<Order> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(SQLConstant.GET_ALL_ORDERS_ID)) {
                while (rs.next()) {
                    list.add(getOrderById(rs.getInt("id")));
                }
            }

        } catch (SQLException throwables) {
            LOGGER.error("Can't get all orders from database", throwables);
            throw new DatabaseException();
        }
        return list;
    }

    @Override
    public List<Order> getAllCustomerOrders(int userId) {
        List<Order> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQLConstant.GET_ALL_ORDERS_ID_BY_USER_ID)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    list.add(getOrderById(rs.getInt("id")));
                }
            }

        } catch (SQLException throwables) {
            LOGGER.error("Can't get all orders for user with id " + userId, throwables);
            throw new DatabaseException();
        }
        return list;
    }

    @Override
    public Order insertOrder(Order order) {
        Connection connection = null;
        PreparedStatement orderStatement = null;
        PreparedStatement servicesStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            orderStatement = connection.prepareStatement(SQLConstant.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            orderStatement.setInt(1, order.getCustomerId());
            orderStatement.setString(2, order.getComment());
            orderStatement.executeUpdate();
            resultSet = orderStatement.getGeneratedKeys();
            if (resultSet.next()) order.setId(resultSet.getInt(1));
            servicesStatement = connection.prepareStatement(SQLConstant.INSERT_SERVICES_TO_ORDER);
            for (Service service : order.getServices()) {
                servicesStatement.setInt(1, order.getId());
                servicesStatement.setInt(2, service.getId());
                servicesStatement.addBatch();
            }
            servicesStatement.executeBatch();
            connection.commit();
        } catch (SQLException ex) {
            Util.rollBack(connection);
            LOGGER.error("Exception in insertOrder", ex);
        } finally {
            Util.close(resultSet, orderStatement, servicesStatement, connection);
        }
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQLConstant.UPDATE_ORDER)){
            statement.setBigDecimal(1, order.getPrice());
            statement.setInt(2, order.getMasterId());
            statement.setString(3, String.valueOf(order.getStatus()));
            statement.setInt(4, order.getId());
            statement.executeUpdate();
        }catch (SQLException ex){
            LOGGER.error("Exception in updateOrder", ex);
            throw new DatabaseException();
        }
    }

    private Order extractOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        while (rs.next()) {
            order.setId(rs.getInt("id"));
            order.setCustomerId(rs.getInt("b.user_account_id"));
            order.setMasterId(rs.getInt("b.master_account_id"));
            order.setPrice(rs.getBigDecimal("b.price"));
            order.addService(new Service(rs.getInt("s.id"), rs.getString("s.name")));
            order.setCreatingTime(rs.getTimestamp("b.creating_time"));
            order.setComment(rs.getString("b.customer_comment"));
            order.setStatus(OrderStatus.valueOf(rs.getString("order_status")));
        }
        return order;
    }
}
