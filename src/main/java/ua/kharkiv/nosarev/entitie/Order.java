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
    private String customerName;
    private String customerSurname;
    private String masterName;
    private String masterSurname;
    private BigDecimal price;
    private List<Service> services;
    private Timestamp creatingTime;
    private String comment;
    private String device;
    private OrderStatus status;

    public Order() {
        services = new ArrayList<>();
        id = 0;
        status = OrderStatus.WAITING_FOR_PROCESSING;
        price = new BigDecimal(0);
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

    public List<Service> getServices() {
        return services;
    }

    public void addService(Service service) {
        services.add(service);
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

    public String getDevice() { return device; }

    public void setDevice(String device) { this.device = device; }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getMasterName() { return masterName; }

    public void setMasterName(String masterName) { this.masterName = masterName; }

    public String getMasterSurname() { return masterSurname; }

    public void setMasterSurname(String masterSurname) { this.masterSurname = masterSurname; }

    public String getCustomerName() { return customerName; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerSurname() { return customerSurname; }

    public void setCustomerSurname(String customerSurname) { this.customerSurname = customerSurname; }

}
