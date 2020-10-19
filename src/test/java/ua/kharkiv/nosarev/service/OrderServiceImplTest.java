package ua.kharkiv.nosarev.service;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.nosarev.dao.api.OrderDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;
import ua.kharkiv.nosarev.service.api.OrderService;
import ua.kharkiv.nosarev.service.api.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {
    private OrderDao orderDao;
    private UserService userService;
    private OrderService orderService;
    private Order plugOrder;
    private Validator validator;

    @Before
    public void init() {
        orderDao = mock(OrderDao.class);
        userService = mock(UserService.class);
        validator = mock(Validator.class);
        orderService = new OrderServiceImpl(orderDao, userService, validator);
        plugOrder = new Order();
        plugOrder.setId(1);
        plugOrder.setPrice(new BigDecimal(150));
        plugOrder.setCustomerId(1);
        plugOrder.setDevice("Device");
        plugOrder.setComment("some comment");
    }

    @Test
    public void getOrderByIdShouldReturnOrderWhenCorrectId() {
        when(orderDao.getOrderById(anyLong())).thenReturn(plugOrder);
        assertEquals(orderService.getOrderById(1), plugOrder);
    }

    @Test
    public void getAllCustomerOrders() {
        List<Order> mockList = mock(List.class);
        when(orderDao.getAllCustomerOrders(anyLong())).thenReturn(mockList);
        assertNotNull(orderService.getAllCustomerOrders(1));
    }

    @Test
    public void insertOrderShouldReturnWrongFieldsMessage() {
        when(validator.validateOrder(plugOrder)).thenReturn(false);
        assertEquals(InfoMessage.WRONG_FIELDS, orderService.insertOrder(plugOrder));
    }

    @Test
    public void insertOrderShouldReturnSuccessMessage() {
        when(validator.validateOrder(plugOrder)).thenReturn(true);
        when(orderDao.insertOrder(plugOrder)).thenReturn(plugOrder);
        assertEquals(InfoMessage.CREATING_ORDER_SUCCESS, orderService.insertOrder(plugOrder));
    }

    @Test
    public void updateOrderShouldReturnWrongFieldsMessage() {
        when(validator.validateOrder(plugOrder)).thenReturn(true);
        when(orderDao.updateOrder(any())).thenReturn(false);
        assertEquals(InfoMessage.WRONG_FIELDS, orderService.updateOrder(plugOrder));
    }

    @Test
    public void updateOrderShouldReturnSuccessMessage() {
        when(validator.validateOrder(plugOrder)).thenReturn(true);
        when(orderDao.updateOrder(any())).thenReturn(true);
        assertEquals(InfoMessage.UPDATING_ORDER_SUCCESS, orderService.updateOrder(plugOrder));
    }

    @Test
    public void updateNewOrderCountShouldReturnCorrectOrdersAmount() {
        HttpServletRequest mockReq = mock(HttpServletRequest.class);
        ServletContext mockContext = mock(ServletContext.class);
        when(mockReq.getServletContext()).thenReturn(mockContext);
        when(orderDao.getNewOrdersAmount()).thenReturn(10L);
        orderService.updateNewOrderCount(mockReq);
        verify(mockContext).setAttribute("countOfNewOrders", 10L);
    }
}
