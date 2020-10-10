package ua.kharkiv.nosarev.services.api;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.PaginationObject;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.entitie.enumeration.PaginationField;

import java.util.List;

public interface OrderService {

    Order getOrderById(long orderId);

    boolean deleteOrderById(long orderId);

    List<Order> getAllCustomerOrders(long userId);

    Order insertOrder(Order order);

    void updateOrder(Order order, long masterId, String price, OrderStatus status);

    int getRowsAmount(PaginationField filter, String filterParam);

    List<Order> findOrders(String paginationSql, PaginationObject pagObject);
}
