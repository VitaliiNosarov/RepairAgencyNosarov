package ua.kharkiv.nosarev.service;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.PaymentDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.Payment;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;
import ua.kharkiv.nosarev.exception.DatabaseException;
import ua.kharkiv.nosarev.service.api.OrderService;
import ua.kharkiv.nosarev.service.api.PaymentService;

public class PaymentServiceImpl implements PaymentService {

    private static final Logger LOGGER = Logger.getLogger(PaymentServiceImpl.class);
    private OrderService orderService;
    private PaymentDao paymentDao;

    public PaymentServiceImpl(OrderService orderService, PaymentDao paymentDao) {
        this.orderService = orderService;
        this.paymentDao = paymentDao;
    }

    @Override
    public InfoMessage payOrder(long orderId) {
        Order order = orderService.getOrderById(orderId);
        try {
            if (savePayment(order)) {
                LOGGER.info("Order with No " + orderId + " was successfully paid");
                return InfoMessage.PAYMENT_SUCCESS;
            }
        } catch (DatabaseException exception) {
            LOGGER.info("Denied attempt to payOrder No" + orderId + exception);
        }
        return InfoMessage.PAYMENT_DENIED;
    }


    private boolean savePayment(Order order) {
        Payment payment = new Payment.Builder()
                .addId(order.getId())
                .addSum(order.getPrice())
                .addUserId(order.getCustomerId()).build();
        return paymentDao.insertPayment(payment);
    }

    @Override
    public Payment getPayment(long orderId) {
        return paymentDao.getPayment(orderId);
    }
}