package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.PaginationObject;

import java.util.List;

public interface OrderDao {

    Order getOrderById(long orderId);

    boolean deleteOrderById(long orderId);

    List<Order> getAllCustomerOrders(long userId);

    Order insertOrder(Order order);

    void updateOrder(Order order);

    List<Order> getOrderRows(String paginationSql, PaginationObject pagObject);

    int getRowsAmount(String filter);
}
