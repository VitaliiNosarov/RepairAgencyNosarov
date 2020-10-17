package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.OrderPaginationObject;

import java.util.List;

public interface OrderDao {

    Order getOrderById(long orderId);

    List<Order> getAllCustomerOrders(long userId);

    Order insertOrder(Order order);

    boolean updateOrder(Order order);

    List<Order> getOrderRows(OrderPaginationObject pagObject);

    void setRowsAmount(OrderPaginationObject pagObject);

    long getNewOrdersAmount();
}
