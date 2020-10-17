package ua.kharkiv.nosarev.dao;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.nosarev.dao.api.OrderDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;

import javax.sql.DataSource;

import static junit.framework.Assert.assertEquals;

public class OrderDaoImplTest {
    private DataSource dataSource;
    private OrderDao orderDao;
    private Order order;

    @Before
    public void setUp() throws Exception {
        dataSource = DBCPDataSource.getDataSource();
        orderDao = new OrderDaoImpl(dataSource);
        order = new Order();
        order.setId(1);
        order.setCustomerId(3);
        order.setDevice("device1");
    }

    @Test
    public void getOrderByIdShouldReturnCorrectOrderFromDB() {
        assertEquals(orderDao.getOrderById(order.getId()).getDevice(), order.getDevice());
    }

    @Test
    public void getAllCustomerOrdersShouldReturnCorrectNumberOfUserOrders() {
        assertEquals(3, orderDao.getAllCustomerOrders(order.getCustomerId()).size());
    }

    @Test
    public void updateOrderShouldCorrectUpdateFields() {
        order = orderDao.getOrderById(1);
        order.setStatus(OrderStatus.WAITING_FOR_PROCESSING);
        orderDao.updateOrder(order);
        assertEquals(OrderStatus.WAITING_FOR_PROCESSING, orderDao.getOrderById(1).getStatus());
        order.setStatus(OrderStatus.READY_TO_ISSUE);
        orderDao.updateOrder(order);
    }

    @Test
    public void getNewOrdersAmountShouldReturnNumberOfWaitingForProcessingStatusOrders() {
        order = orderDao.getOrderById(1);
        order.setStatus(OrderStatus.WAITING_FOR_PROCESSING);
        orderDao.updateOrder(order);
        assertEquals(1, orderDao.getNewOrdersAmount());
        order.setStatus(OrderStatus.READY_TO_ISSUE);
        orderDao.updateOrder(order);
    }
}