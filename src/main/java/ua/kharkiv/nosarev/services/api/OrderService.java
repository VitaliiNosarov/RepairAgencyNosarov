package ua.kharkiv.nosarev.services.api;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.PaginationObject;
import ua.kharkiv.nosarev.entitie.enumeration.PaginationField;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {

    Order getOrderById(long orderId);

    boolean deleteOrderById(long orderId);

    List<Order> getAllCustomerOrders(long userId);

    String insertOrder(Order order);

    //update 4 fields
    String updateOrder(Order order);

    int getRowsAmount(PaginationField filter, String filterParam);

    List<Order> findOrders(String paginationSql, PaginationObject pagObject);

    public void updateNewOrderCount(HttpServletRequest req);
}
