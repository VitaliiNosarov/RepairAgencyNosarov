package ua.kharkiv.nosarev.entitie;

import ua.kharkiv.nosarev.entitie.enumeration.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment extends AbstractEntity{


    private long userId;
    private BigDecimal summ;
    private PaymentType type;
    private LocalDateTime paymentTime;
    private long orderId;

}
