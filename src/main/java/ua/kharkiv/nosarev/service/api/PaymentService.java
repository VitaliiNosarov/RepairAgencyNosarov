package ua.kharkiv.nosarev.service.api;

import ua.kharkiv.nosarev.entitie.Payment;

public interface PaymentService {

    Payment getPayment(long orderId);

    public String payOrder(long orderId);
}
