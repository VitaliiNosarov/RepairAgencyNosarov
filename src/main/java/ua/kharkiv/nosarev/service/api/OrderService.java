package ua.kharkiv.nosarev.service.api;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {

    Order getOrderById(long orderId);

    List<Order> getAllCustomerOrders(long userId);

    InfoMessage insertOrder(Order order);

    //update 4 fields
    InfoMessage updateOrder(Order order);

    List<Order> findOrders(HttpServletRequest request);

    public void updateNewOrderCount(HttpServletRequest req);
}
