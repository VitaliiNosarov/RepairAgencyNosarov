package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.Order;

import java.util.List;

public interface OrderDao {

    Order getOrderById(int orderId);

    boolean deleteOrderById(int orderId);

    List<Order> getAllOrders();

    List<Order> getAllCustomerOrders(int userId);

    Order insertOrder(Order order);

    Order updateOrder(Order order);
}
