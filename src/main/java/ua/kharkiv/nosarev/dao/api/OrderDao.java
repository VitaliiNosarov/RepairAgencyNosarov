package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.enumeration.Table;

import java.util.List;

public interface OrderDao {

    Order getOrderById(int orderId);

    boolean deleteOrderById(int orderId);

    List<Order> getAllOrders();

    List<Order> getAllCustomerOrders(int userId);

    Order insertOrder(Order order);

    void updateOrder(Order order);

    List<Order> getRows(int start, int recordsPerPage);

    int getRowsAmount();
}
