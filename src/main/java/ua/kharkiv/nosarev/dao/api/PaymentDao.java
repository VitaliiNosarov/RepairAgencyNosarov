package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.Payment;

public interface PaymentDao {

    /**
     * Insert Payment to Database when customer balance == paymentSum or paymentSum is less than customer balance.
     * change order status to PAID, customer balance to current - payment sum and save Payment to Database
     *
     * @param payment not null payment with Sum, id(orderId) and customerId
     * @return boolean result of operation
     */
    boolean insertPayment(Payment payment);

    /**
     * Return Payment which contains sum, creating time and customer id from database
     *
     * @param orderId should be >0
     * @return Payment with same orderId
     */
    Payment getPayment(long orderId);

}
