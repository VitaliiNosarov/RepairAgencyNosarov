package ua.kharkiv.nosarev.entitie;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private int id;
    private int customerId;
    private int masterId;
    private BigDecimal price;
    private List<Service> servicesList;
    private Payment payment;
    private LocalDateTime creatingTime;
}
