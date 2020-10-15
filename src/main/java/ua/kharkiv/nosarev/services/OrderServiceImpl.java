package ua.kharkiv.nosarev.services;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;
import ua.kharkiv.nosarev.dao.api.OrderDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.PaginationObject;
import ua.kharkiv.nosarev.entitie.enumeration.PaginationField;
import ua.kharkiv.nosarev.exception.ServiceException;
import ua.kharkiv.nosarev.services.api.OrderService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);
    private OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order getOrderById(long orderId) {
        if (orderId != 0) {
            return orderDao.getOrderById(orderId);
        }
        LOGGER.error("Service Exception in getOrderById, wrong orderId " + orderId);
        throw new ServiceException();
    }

    @Override
    public boolean deleteOrderById(long orderId) {
        boolean result = false;
        if (orderId != 0) {
            result = orderDao.deleteOrderById(orderId);
        }
        return result;
    }

    @Override
    public List<Order> getAllCustomerOrders(long userId) {
        if (userId != 0) {
            return orderDao.getAllCustomerOrders(userId);
        }
        LOGGER.error("Service Exception in getAllCustomerOrders, wrong userId " + userId);
        throw new ServiceException();
    }

    @Override
    public String insertOrder(Order order) {
        if (Validator.validateOrder(order)) {
            orderDao.insertOrder(order);
            return InfoMessage.CREATING_ORDER.toString();
        }
        LOGGER.error("Service Exception in insertOrder " + order);
        return InfoMessage.WRONG_FIELDS.toString();
    }

    @Override
    public String updateOrder(Order order) {
        boolean result = false;
        if (order == null) {
            LOGGER.error("Service Exception in updateOrder " + order);
        }
        result = orderDao.updateOrder(order);
        if (result) {
            return InfoMessage.UPDATING_ORDER.toString();
        }
        return InfoMessage.WRONG_FIELDS.toString();
    }

    @Override
    public int getRowsAmount(PaginationField filter, String filterParam) {
        String filterString = "";
        if (filterParam != null && filterParam.length() > 0) {
            filterString = filter.getQuery() + " = '" + filterParam + "'";
        }
        return orderDao.getRowsAmount(filterString);
    }


    @Override
    public List<Order> findOrders(String paginationSql, PaginationObject pagObject) {
        return orderDao.getOrderRows(paginationSql, pagObject);
    }

    @Override
    public void updateNewOrderCount(HttpServletRequest req) {
        ServletContext context = req.getServletContext();
        int countOfNewOrders = orderDao.getNewOrdersAmount();
        context.setAttribute("countOfNewOrders", countOfNewOrders);
    }
}
