package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.Payment;

public interface PaymentDao {

    void insertPayment(Payment payment);

    Payment getPayment(long userId);
}
