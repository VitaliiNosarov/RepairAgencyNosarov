package ua.kharkiv.nosarev.entitie.enumeration;

public enum PaginationField {

    CREATING_TIME("creating_time"),
    PRICE("price"),
    STATUS("order_status"),
    MASTER("master_account_id");

    private String query;

    PaginationField(String name) {
        this.query = name;
    }

    public String getQuery() {
        return query;
    }
}
