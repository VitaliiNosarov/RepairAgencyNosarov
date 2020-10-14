package ua.kharkiv.nosarev.entitie.enumeration;

public enum OrderStatus {

    WAITING_FOR_PROCESSING("Waiting for processing","Ожидает обработки"),
    WAITING_FOR_PAYMENT("Waiting for payment","Ожидает оплаты"),
    PAID("Paid", "Оплачен"),
    CANCELED("Canceled", "Отменен"),
    IN_WORK("In work", "В работе"),
    READY_TO_ISSUE("Ready to issue", "Готов к выдаче клиенту"),
    COMPLETED("Completed", "Завершен");

    private String name;
    private String name2;

    OrderStatus(String name, String name2) {
        this.name = name;
        this.name2 = name2;
    }

    public String getValue() {
        return name;
    }
    public String getValue2() {
        return name;
    }
}
