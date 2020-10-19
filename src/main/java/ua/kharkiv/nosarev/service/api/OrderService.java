package ua.kharkiv.nosarev.service.api;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {

    /**
     * Check id and return Order from database where id == orderId
     *
     * @param orderId
     * @return Order with id equal orderId
     */
    Order getOrderById(long orderId);

    /**
     * Check id and return all Orders where customerId == userId
     *
     * @param userId
     * @return Lis<Order> where customerId = userId
     */
    List<Order> getAllCustomerOrders(long userId);

    /**
     * Validate and save Order to Database. Return InfoMessage as a result of operation.
     * Return CREATING_ORDER_SUCCESS where result is true and WRONG_FIELDS when result is false
     *
     * @param order
     * @return InfoMessage as a result
     */
    InfoMessage insertOrder(Order order);

    /**
     * @return List of all Orders from Database
     */
    public List<Order> getAllOrders();

    /**
     * Validate and update status, masterId and price in current Order in Database.
     * Return InfoMessage as a result of operation.
     * Return UPDATING_ORDER_SUCCESS when result is true and Wrong fields when result is false
     *
     * @param order
     * @return InfoMessage as a result
     */
    InfoMessage updateOrder(Order order);

    /**
     * Need int currentPage, int recordsPerPage, String orderBy, String filter,
     * String filterParam with the same names in request and returns List<Order> sorting by orderBy param,
     * filtrated by filter and filterParam. Also set parameters to request :
     * numberOfPages, amountOfRows, currentPage, recordsPerPage, orderBy, filter, filterParam.
     * when filter == STATUS add param statuses, when  filter == MASTER add param masters
     *
     * @param request (add new parameters to request)
     * @return List Orders
     */
    List<Order> findOrders(HttpServletRequest request);

    /**
     * Set number of Order with status WAITING_FOR_PROCESSING from Database to req with paramName = countOfNewOrders
     *
     * @param req (add new parameter to request)
     */
    public void updateNewOrderCount(HttpServletRequest req);

    /**
     * Get List of all Orders from Database and upload to xls file with address = path
     *
     * @param path address of xls file
     */
    public void uploadOrdersToExel(String path);
}
