package ua.kharkiv.nosarev.service.api;

import ua.kharkiv.nosarev.entitie.Payment;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;

public interface PaymentService {

    Payment getPayment(long orderId);

    InfoMessage payOrder(long orderId);
}
