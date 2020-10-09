package ua.kharkiv.nosarev.entitie.enumeration;

public enum OrderStatus {

    WAITING_FOR_PROCESSING("Waiting for processing"),
    WAITING_FOR_PAYMENT("Waiting for payment"),
    PAID("Paid"),
    CANCELED("Canceled"),
    IN_WORK("In work"),
    READY_TO_ISSUE("Ready to issue"),
    COMPLETED("Completed");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
