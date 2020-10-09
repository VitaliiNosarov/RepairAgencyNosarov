package ua.kharkiv.nosarev.dao;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.OrderDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.exception.DatabaseException;
import ua.kharkiv.nosarev.util.DaoUtil;

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
        Order order = new Order();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstant.GET_ORDER_BY_ID)) {
            statement.setInt(1, orderId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    mapResultSetToOrder(rs, order);
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
        boolean result;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstant.DELETE_ORDER_BY_ID)) {
            statement.setInt(1, orderId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            LOGGER.error("Can't delete order with id " + orderId + "from database", throwables);
            throw new DatabaseException();
        }
        return result;
    }

    @Override
    public List<Order> getAllCustomerOrders(int userId) {
        List<Order> list;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstant.GET_ALL_ORDERS_BY_USER_ID)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                list = getOrderListFromResultSet(rs);
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
            orderStatement.setLong(1, order.getCustomerId());
            orderStatement.setString(2, order.getDevice());
            orderStatement.setString(3, order.getComment());
            orderStatement.executeUpdate();
            resultSet = orderStatement.getGeneratedKeys();
            if (resultSet.next()) order.setId(resultSet.getInt(1));
            servicesStatement = connection.prepareStatement(SQLConstant.INSERT_SERVICES_TO_ORDER);
            for (Service service : order.getServices()) {
                servicesStatement.setLong(1, order.getId());
                servicesStatement.setLong(2, service.getId());
                servicesStatement.addBatch();
            }
            servicesStatement.executeBatch();
            connection.commit();
        } catch (SQLException ex) {
            LOGGER.error("Exception in insertOrder", ex);
            DaoUtil.rollBack(connection);
            throw new DatabaseException();
        } finally {
            DaoUtil.close(resultSet, orderStatement, servicesStatement, connection);
        }
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstant.UPDATE_ORDER)) {
            statement.setBigDecimal(1, order.getPrice());
            statement.setLong(2, order.getMasterId());
            statement.setString(3, String.valueOf(order.getStatus()));
            statement.setLong(4, order.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error("Exception in updateOrder", ex);
            throw new DatabaseException();
        }
    }

    @Override
    public int getRowsAmount() {
        int amountOfRows = 0;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery((SQLConstant.GET_AMOUNT_OF_ORDERS))) {
                if (rs.next()) {
                    amountOfRows = rs.getInt("count");
                }
            }
        } catch (SQLException ex) {
            LOGGER.error("SQL Exception in getRowsAmount " + ex);
            throw new DatabaseException();
        }
        return amountOfRows;
    }

    @Override
    public List<Order> getOrderRows(int startPosition, int recordsPerPage, String orderBy, boolean isReverse, String filter) {

        List<Order> orderList;
        String reverse = "";
        String filterString = "";
        if (isReverse) {
            reverse = SQLConstant.REVERSE;
        }
        if (filter.length() > 1) {
            filterString = SQLConstant.WHERE + filter;
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(SQLConstant.FIND_ORDERS + filterString + SQLConstant.GROUP_BY_ID
                             + SQLConstant.ORDER_BY + orderBy + reverse + SQLConstant.LIMIT)) {
            statement.setInt(1, startPosition);
            statement.setInt(2, recordsPerPage);
            try (ResultSet rs = statement.executeQuery()) {
                orderList = getOrderListFromResultSet(rs);
            }
        } catch (SQLException ex) {
            LOGGER.error("SQL Exception in getRowsAmount " + ex);
            throw new DatabaseException();
        }
        return orderList;
    }

    private List<Order> getOrderListFromResultSet(ResultSet rs) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        while (rs.next()) {
            Order order = new Order();
            mapResultSetToOrder(rs, order);
            orderList.add(order);
        }
        return orderList;
    }

    private void mapResultSetToOrder(ResultSet rs, Order order) throws SQLException {
        order.setId(rs.getInt("id"));
        order.setCustomerId(rs.getInt("b.user_account_id"));
        order.setMasterId(rs.getInt("b.master_account_id"));
        order.setMasterName(rs.getString("master_name"));
        order.setMasterSurname(rs.getString("master_surname"));
        order.setCustomerName(rs.getString("customer_name"));
        order.setCustomerSurname(rs.getString("customer_surname"));
        order.setPrice(rs.getBigDecimal("b.price"));
        order.setCreatingTime(rs.getTimestamp("b.creating_time"));
        order.setComment(rs.getString("b.customer_comment"));
        order.setDevice(rs.getString("b.device"));
        order.setStatus(OrderStatus.valueOf(rs.getString("order_status")));
        addServicesToOrder(rs.getString("services"), order);
    }

    private Order addServicesToOrder(String services, Order order) {
        String[] servicesArray = services.split(",");
        for (String str : servicesArray) {
            String[] strService = str.split(" ");
            Service service = new Service();
            service.setId(Long.parseLong(strService[0]));
            service.setName(strService[1]);
            order.addService(service);
        }
        return order;
    }
}
