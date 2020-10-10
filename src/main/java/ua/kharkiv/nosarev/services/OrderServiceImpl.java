package ua.kharkiv.nosarev.services;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.OrderDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.PaginationObject;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.entitie.enumeration.PaginationField;
import ua.kharkiv.nosarev.exception.ServiceException;
import ua.kharkiv.nosarev.services.api.OrderService;

import java.math.BigDecimal;
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
    public Order insertOrder(Order order) {
        if (Validator.validateOrder(order)) {
            return orderDao.insertOrder(order);
        }
        LOGGER.error("Service Exception in insertOrder " + order);
        throw new ServiceException();
    }

    @Override
    public void updateOrder(Order order, long masterId, String price, OrderStatus status) {
        if (order == null) {
            LOGGER.error("Service Exception in updateOrder " + order);
            throw new ServiceException();
        }
        order.setMasterId(masterId);
        order.setStatus(status);
        try {
            if (price != "") {
                BigDecimal amount = new BigDecimal(price);
                order.setPrice(amount);
            }
        } catch (NumberFormatException ex) {
            LOGGER.info("Wrong price " + price);
            throw new ServiceException();
        }
        orderDao.updateOrder(order);
    }

    @Override
    public int getRowsAmount(PaginationField filter, String filterParam) {
        String filterString = "";
        if (filterParam != null && filterParam.length() > 0) {
            filterString = filter.getName() + " = '" + filterParam + "'";
        }
        return orderDao.getRowsAmount(filterString);
    }

    @Override
    public List<Order> findOrders(String paginationSql, PaginationObject pagObject) {
            return orderDao.getOrderRows(paginationSql, pagObject);
    }
}
