package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.User;

import java.util.List;

public interface OrderDao {

    Order getOrderByUserId(int userId);

    boolean deleteOrderById(int orderId);

    List<Order> getAllOrders();

    Order insertOrder(Order order);
}
