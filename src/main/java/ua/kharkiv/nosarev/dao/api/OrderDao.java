package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.Order;

import java.util.List;

public interface OrderDao {

    Order getOrderById(int orderId);

    boolean deleteOrderById(int orderId);

    List<Order> getAllCustomerOrders(int userId);

    Order insertOrder(Order order);

    void updateOrder(Order order);

    List<Order> getOrderRows(int start, int recordsPerPage, String orderBy, boolean isReverse, String filter);

    int getRowsAmount();
}
