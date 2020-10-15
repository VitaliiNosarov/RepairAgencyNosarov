package ua.kharkiv.nosarev.services;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;
import ua.kharkiv.nosarev.dao.api.PaymentDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.Payment;
import ua.kharkiv.nosarev.exception.DatabaseException;
import ua.kharkiv.nosarev.exception.ServiceException;
import ua.kharkiv.nosarev.services.api.OrderService;
import ua.kharkiv.nosarev.services.api.PaymentService;

public class PaymentServiceImpl implements PaymentService {

    private static final Logger LOGGER = Logger.getLogger(PaymentServiceImpl.class);
    private OrderService orderService;
    private PaymentDao paymentDao;

    public PaymentServiceImpl(OrderService orderService, PaymentDao paymentDao) {
        this.orderService = orderService;
        this.paymentDao = paymentDao;
    }

    public String payOrder(long orderId) {
        Order order = orderService.getOrderById(orderId);
        try {
            if (savePayment(order)) {
                LOGGER.info("Order with No " + orderId + " was successfully paid");
                return InfoMessage.PAYMENT_SUCCESS.toString();
            }
        } catch (DatabaseException exception) {
            LOGGER.info("Denied attempt to payOrder No" + orderId + exception);
        }
        LOGGER.info("Denied attempt to payOrder No" + orderId + ". not enough money on balance");
        return InfoMessage.PAYMENT_DENIED.toString();
    }


    private boolean savePayment(Order order) throws DatabaseException {
        Payment payment = new Payment.Builder()
                .addId(order.getId())
                .addSum(order.getPrice())
                .addUserId(order.getCustomerId()).build();
        return paymentDao.insertPayment(payment);
    }

    @Override
    public Payment getPayment(long orderId) {
        if (orderId > 0) {
            return paymentDao.getPayment(orderId);
        } else {
            LOGGER.error("Service exception in getPayment");
            throw new ServiceException();
        }
    }
}