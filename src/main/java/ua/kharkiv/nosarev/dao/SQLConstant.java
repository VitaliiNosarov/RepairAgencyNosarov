package ua.kharkiv.nosarev.dao;

public class SQLConstant {

    public static final String GET_USER_BY_EMAIL_PASS = "SELECT id, name, surname, phone, email, password, balance, locale, role " +
            "FROM account where email = ?;";
    public static final String DELETE_USER_BY_EMAIL = "DELETE FROM account WHERE email = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM account";
    public static final String INSERT_USER = "INSERT INTO account (name, surname, phone, email, password, balance, locale, role) \n" +
            " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";


    public static final String GET_ORDER_BY_ID = "SELECT b.id, b.price, b.customer_comment, b.creating_time," +
            " b.master_account_id, b.order_status, b.payment_id, b.user_account_id, s.name from booking AS b " +
            "LEFT JOIN booking_service AS b_s ON b_s.booking_id = b.id LEFT JOIN service AS s" +
            " ON s.id = b_s.service_id WHERE b.id = ?";
    public static final String GET_ALL_ORDERS_ID_BY_USER_ID = "SELECT id FROM booking WHERE user_account_id  = ?";
    public static final String GET_ALL_ORDERS_ID = "SELECT id FROM booking";
    public static final String DELETE_ORDER_BY_ID = "DELETE FROM order WHERE id = ?";
    public static final String INSERT_ORDER = "INSERT INTO booking (price, customer_comment, master_account_id, order_status, user_account_id) \n" +
            "VALUES ( ?, ?, ?, ?, ?);";
    public static final String INSERT_SERVICES_TO_ORDER = "INSERT INTO booking_service (booking_id, service_id)\n" +
            "SELECT booking.id, service.id FROM booking, service where booking.id = ? AND service.name = ?";
}
