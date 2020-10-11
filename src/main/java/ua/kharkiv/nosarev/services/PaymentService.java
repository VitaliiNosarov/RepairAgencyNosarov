package ua.kharkiv.nosarev.services;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.MessageType;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.exception.DatabaseException;
import ua.kharkiv.nosarev.exception.RegistrationException;
import ua.kharkiv.nosarev.services.api.OrderService;
import ua.kharkiv.nosarev.services.api.UserService;

import java.math.BigDecimal;

public class PaymentService {

    private static final Logger LOGGER = Logger.getLogger(PaymentService.class);
    private OrderService orderService;
    private UserService userService;

    public PaymentService(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    public String payOrder(long orderId) {
        Order order = orderService.getOrderById(orderId);
        User user = userService.getUserById(order.getCustomerId());
        BigDecimal userBalance = user.getBalance();
        BigDecimal orderPrice = order.getPrice();
        if (user.getBalance().compareTo(orderPrice) == 1) {
            userBalance = userBalance.subtract(orderPrice);
            user.setBalance(userBalance);
            order.setStatus(OrderStatus.PAID);
            try {
                orderService.updateOrder(order);
                userService.updateUser(user);
                LOGGER.info("Order with No " + orderId + " was successfully paid");
                return MessageType.PAYMENT_SUCCESS.getMessage();
            } catch (RegistrationException | DatabaseException exception) {
            }
        }
        LOGGER.info("Denied attempt to payOrder No" + orderId + ". not enough money on balance");
        return MessageType.PAYMENT_DENIED.getMessage();
    }
}
