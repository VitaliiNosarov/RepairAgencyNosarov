package ua.kharkiv.nosarev.services.api;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.enumeration.PaginationField;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.services.OrderPaginationObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {

    Order getOrderById(long orderId);

    boolean deleteOrderById(long orderId);

    List<Order> getAllCustomerOrders(long userId);

    String insertOrder(Order order);

    //update 4 fields
    String updateOrder(Order order);

    List<Order> findOrders(HttpServletRequest request);

    public void updateNewOrderCount(HttpServletRequest req);
}
