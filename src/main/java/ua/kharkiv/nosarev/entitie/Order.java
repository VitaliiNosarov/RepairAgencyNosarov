package ua.kharkiv.nosarev.entitie;

import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private int customerId;
    private int masterId;
    private BigDecimal price;
    private List<String> services;
    private int paymentId;
    private Timestamp creatingTime;
    private String comment;
    private OrderStatus status;

    public Order() {
        services = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getServices() {
        return services;
    }

    public void addService(String service) {
        services.add(service);
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Timestamp getCreatingTime() {
        return creatingTime;
    }

    public void setCreatingTime(Timestamp creatingTime) {
        this.creatingTime = creatingTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
