package ua.kharkiv.nosarev.entitie;

import ua.kharkiv.nosarev.entitie.enumeration.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment{

    private int id;
    private int userId;
    private BigDecimal summ;
    private PaymentType type;
    private LocalDateTime paymentTime;
    private int orderId;

}
