package ua.kharkiv.nosarev.service;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.OrderDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.OrderPaginationObject;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.entitie.enumeration.PaginationField;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.exception.ServiceException;
import ua.kharkiv.nosarev.service.api.OrderService;
import ua.kharkiv.nosarev.service.api.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);
    private OrderDao orderDao;
    private UserService userService;
    private Validator validator;

    public OrderServiceImpl(OrderDao orderDao, UserService userService, Validator validator) {
        this.orderDao = orderDao;
        this.userService = userService;
        this.validator = validator;
    }

    @Override
    public Order getOrderById(long orderId) {
        if (orderId > 0) {
            return orderDao.getOrderById(orderId);
        }
        LOGGER.error("Service Exception in getOrderById, wrong orderId " + orderId);
        throw new ServiceException();
    }

    @Override
    public List<Order> getAllCustomerOrders(long userId) {
        if (userId > 0) {
            return orderDao.getAllCustomerOrders(userId);
        }
        LOGGER.error("Service Exception in getAllCustomerOrders, wrong userId " + userId);
        throw new ServiceException();
    }

    @Override
    public InfoMessage insertOrder(Order order) {
        if (validator.validateOrder(order)) {
            orderDao.insertOrder(order);
            return InfoMessage.CREATING_ORDER_SUCCESS;
        }
        LOGGER.error("Service Exception in insertOrder " + order);
        return InfoMessage.WRONG_FIELDS;
    }

    @Override
    public InfoMessage updateOrder(Order order) {
        if (validator.validateOrder(order)) {
            if (orderDao.updateOrder(order)) {
                return InfoMessage.UPDATING_ORDER_SUCCESS;
            }
        }
        LOGGER.error("Service Exception in updateOrder " + order);
        return InfoMessage.WRONG_FIELDS;
    }


    @Override
    public List<Order> findOrders(HttpServletRequest req) {
        OrderPaginationObject pagObject = getPgObjectFromRequest(req);
        List<Order> orders = orderDao.getOrderRows(pagObject);
        setPaginationAttributes(req, pagObject);
        return orders;
    }

    private OrderPaginationObject getPgObjectFromRequest(HttpServletRequest req) {

        OrderPaginationObject pagObj;
        try {
            int currentPage = Integer.parseInt(req.getParameter("currentPage"));
            int recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
            String filter = req.getParameter("filter");
            String filterParam = req.getParameter("filterParam");
            boolean reverse = Boolean.parseBoolean(req.getParameter("reverse"));
            String orderBy = req.getParameter("orderBy");

            pagObj = new OrderPaginationObject.Builder(currentPage, recordsPerPage).addFilter(filter, filterParam)
                    .addSorting(orderBy, reverse).addLimit().build();

            orderDao.setRowsAmount(pagObj);
            int nOfPages = pagObj.getAmountOfRows() / recordsPerPage;
            if (pagObj.getAmountOfRows() % recordsPerPage > 0) {
                nOfPages++;
            }
            pagObj.setNumberOfPages(nOfPages);
        } catch (IllegalArgumentException | NullPointerException exception) {
            LOGGER.error("Exception in getOrderPaginationParams. Wrong Arguments", exception);
            throw new ServiceException();
        }
        return pagObj;
    }

    private HttpServletRequest setPaginationAttributes(HttpServletRequest req, OrderPaginationObject pagObject) {
        if (pagObject.getFilter() != null) {
            req.setAttribute("filter", pagObject.getFilter());
            if (pagObject.getFilterParam() != null) {
                req.setAttribute("filterParam", pagObject.getFilterParam());
            }
        }
        if (PaginationField.STATUS.equals(pagObject.getFilter())) {
            Set orderStatuses = EnumSet.allOf(OrderStatus.class);
            req.setAttribute("statuses", orderStatuses);
        }
        if (PaginationField.MASTER.equals(pagObject.getFilter())) {
            List<User> listOfMasters = userService.getAllUsersByRole(UserRole.MASTER);
            req.setAttribute("masters", listOfMasters);
        }

        req.setAttribute("reverse", pagObject.isReverse());
        req.setAttribute("noOfPages", pagObject.getNumberOfPages());
        req.setAttribute("currentPage", pagObject.getCurrentPage());
        req.setAttribute("recordsPerPage", pagObject.getRecordsPerPage());
        req.setAttribute("orderBy", pagObject.getOrderBy());
        return req;
    }

    @Override
    public void updateNewOrderCount(HttpServletRequest req) {
        ServletContext context = req.getServletContext();
        long countOfNewOrders = orderDao.getNewOrdersAmount();
        context.setAttribute("countOfNewOrders", countOfNewOrders);
    }
}
