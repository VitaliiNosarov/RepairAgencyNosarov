package ua.kharkiv.nosarev.entitie.enumeration;

public enum Table {
    ACCOUNT("account"),
    BOOKING("booking"),
    SERVICE("service"),
    PAYMENT("payment"),
    FEEDBACK("feedback"),
    ORDER_DERVICE("order_service");

    private String name;

    Table(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
