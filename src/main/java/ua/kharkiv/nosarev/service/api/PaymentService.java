package ua.kharkiv.nosarev.service.api;

import ua.kharkiv.nosarev.entitie.Payment;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;

public interface PaymentService {

    /**
     * @param orderId
     * @return Payment from Database where id = orderId
     */
    Payment getPayment(long orderId);

    /**
     * Insert Payment to Database when customer balance == paymentSum or paymentSum is less than customer balance.
     * change order status to PAID, customer balance to current - payment sum and save Payment to Database
     * return InfoMessage as a result of operation.
     *
     * @param orderId
     * @return PAYMENT_SUCCESS when result is true and PAYMENT_DENIED when result is false
     */
    InfoMessage payOrder(long orderId);
}
