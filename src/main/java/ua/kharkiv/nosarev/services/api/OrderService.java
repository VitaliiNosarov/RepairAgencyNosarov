package ua.kharkiv.nosarev.services.api;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;

import java.util.List;

public interface OrderService {

    Order getOrderById(int orderId);

    boolean deleteOrderById(int orderId);

    List<Order> getAllCustomerOrders(int userId);

    Order insertOrder(Order order);

    void updateOrder(Order order, int masterId, String price, OrderStatus status);

    int getRowsAmount();

    List<Order> findOrders(int currentPage, int recordsPerPage, String orderBy, String reverse, String filter, String filterParam);
}
