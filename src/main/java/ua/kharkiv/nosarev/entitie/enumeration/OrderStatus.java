package ua.kharkiv.nosarev.entitie.enumeration;

public enum OrderStatus {

    WAITING_FOR_PROCESSING(1),
    WAITING_FOR_PAYMENT(2),
    PAID(3),
    CANCELED(4),
    IN_WORK(5),
    READY_TO_ISSUE(6),
    COMPLETED(7);

    private int id;

    OrderStatus(int id) {
        this.id = id;
    }

    public boolean equalsTo(int code) {
        return id == code;
    }

    public int getId() {
        return id;
    }

}
