package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.OrderPaginationObject;

import java.util.List;

public interface OrderDao {

    /**
     * Return Order from Database with the same orderId
     *
     * @param orderId should be>0
     * @return Order
     */
    Order getOrderById(long orderId);

    /**
     * Return List of Orders from Database where id = userId
     *
     * @param userId should be>0
     * @return List<Order>
     */
    List<Order> getAllCustomerOrders(long userId);

    /**
     * Save not null Order to Database and return itself with new Id and generated Time from Database
     *
     * @param order not null
     * @return same Order as in params but with generated id and creating time
     */
    Order insertOrder(Order order);

    /**
     * Update status, masterId and price in current Order in Database. return boolean result of operation
     *
     * @param order not null
     * @return boolean result of operation
     */
    boolean updateOrder(Order order);

    /**
     * @return All Orders from Database
     */
    public List<Order> getAllOrders();

    /**
     * Need correct PaginationObject which contains SQL query for pagination, sorting, and filtration.
     * Returns number of Orders = OrderPaginationObject.recordsPerPage from database with sorting and filtration
     * which is set in OrderPaginationObject.sqlQuery, OrderPaginationObject.filterQuery
     * and OrderPaginationObject.filterParam
     *
     * @param pagObject not null
     * @return List with number of Orders as recordsPerPage
     */
    List<Order> getOrderRows(OrderPaginationObject pagObject);

    /**
     * Set general amount of orders from Database to OrderPaginationObject which is need for getOrderRows
     *
     * @param pagObject not null, contains filterQuery
     */
    void setRowsAmount(OrderPaginationObject pagObject);

    /**
     * Return long number of Orders where orderStatus == WAITING_FOR_PROCESSING
     *
     * @return number of WAITING_FOR_PROCESSING orders
     */
    long getNewOrdersAmount();
}
