package ua.kharkiv.nosarev.services.api;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    Order getOrderById(int orderId);

    boolean deleteOrderById(int orderId);

    List<Order> getAllOrders();

    List<Order> getAllCustomerOrders(int userId);

    Order insertOrder(Order order);

    void updateOrder(Order order, int masterId, String price, OrderStatus status);
}
