package ua.kharkiv.nosarev.service;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.nosarev.dao.api.PaymentDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.Payment;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;
import ua.kharkiv.nosarev.exception.DatabaseException;
import ua.kharkiv.nosarev.service.api.OrderService;
import ua.kharkiv.nosarev.service.api.PaymentService;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaymentServiceImplTest {

    private OrderService orderService;
    private PaymentDao paymentDao;
    private PaymentService paymentService;
    private Order plugOrder;
    private Payment payment;

    @Before
    public void init() {
        orderService = mock(OrderService.class);
        paymentDao = mock(PaymentDao.class);
        paymentService = new PaymentServiceImpl(orderService, paymentDao);
        plugOrder = new Order();
        plugOrder.setId(1);
        plugOrder.setPrice(new BigDecimal(150));
        plugOrder.setCustomerId(1);
        payment = new Payment.Builder().addId(1).addSum(plugOrder.getPrice())
                .addUserId(1).build();
    }

    @Test
    public void payOrderShouldReturnDenyMessage() {
        when(paymentDao.insertPayment(any())).thenReturn(false);
        when(orderService.getOrderById(1)).thenReturn(plugOrder);
        assertEquals(InfoMessage.PAYMENT_DENIED, paymentService.payOrder(1));
    }

    @Test
    public void payOrderShouldReturnSuccessMessage() {
        when(paymentDao.insertPayment(any())).thenReturn(true);
        when(orderService.getOrderById(1)).thenReturn(plugOrder);
        assertEquals(InfoMessage.PAYMENT_SUCCESS, paymentService.payOrder(1));
    }

    @Test
    public void payOrderShouldReturnDenyMessageWhenDBExceptionInDao() {
        when(paymentDao.insertPayment(any())).thenThrow(new DatabaseException());
        when(orderService.getOrderById(1)).thenReturn(plugOrder);
        assertEquals(InfoMessage.PAYMENT_DENIED, paymentService.payOrder(1));
    }

    @Test
    public void getPaymentShouldReturnPayment() {
        when(paymentDao.getPayment(1)).thenReturn(payment);
        assertEquals(paymentService.getPayment(1).getClass(), Payment.class);
    }
}