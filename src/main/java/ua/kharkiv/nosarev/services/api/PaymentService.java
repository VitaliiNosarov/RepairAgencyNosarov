package ua.kharkiv.nosarev.services.api;

import ua.kharkiv.nosarev.entitie.Payment;

public interface PaymentService {

    Payment getPayment(long orderId);

    public String payOrder(long orderId);
}
