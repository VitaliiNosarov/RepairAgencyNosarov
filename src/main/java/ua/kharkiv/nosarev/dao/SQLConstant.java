package ua.kharkiv.nosarev.dao;

public class SQLConstant {

    public static final String GET_USER_BY_EMAIL_PASS = "SELECT * FROM account where email = ?;";
    public static final String GET_USER_BY_ID = "SELECT * FROM account where id = ?;";
    public static final String DELETE_USER_BY_EMAIL = "DELETE FROM account WHERE id = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM account";
    public static final String GET_ALL_USERS_BY_ROLE = "SELECT * FROM account WHERE role = ?";
    public static final String GET_ROLE_BY_USER_ID = "select role from account where id = ?";
    public static final String SAVE_USER = "INSERT INTO ACCOUNT (id, email, password, name, surname, role, phone, locale, balance)" +
            " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
            " AS new on duplicate key update id = new.id, email=new.email, password=new.password, name=new.name," +
            " surname=new.surname, role = new.role, phone=new.phone, locale = new.locale, balance = new.balance;";

    public static final String DELETE_ORDER_BY_ID = "DELETE FROM order WHERE id = ?";
    public static final String INSERT_ORDER = "INSERT INTO booking (user_account_id, device, customer_comment) VALUES (?, ?, ?);";
    public static final String INSERT_SERVICES_TO_ORDER = "INSERT INTO booking_service (booking_id, service_id) VALUES (?, ?);";
    public static final String UPDATE_ORDER = "UPDATE booking SET price = ?, master_account_id = ?, order_status = ? WHERE id = ?;";
    public static final String GET_ALL_ORDERS = "SELECT b.id, b.price, b.customer_comment, b.device, b.creating_time, " +
            "b.master_account_id, account.name AS customer_name, account.surname As customer_surname, " +
            "b.user_account_id, acc.name AS master_name, acc.surname AS master_surname, " +
            "b.order_status, s.id AS service_id, s.name from booking AS b LEFT JOIN booking_service AS b_s ON b_s.booking_id = b.id" +
            " LEFT JOIN service AS s ON s.id = b_s.service_id left join account as acc on b.master_account_id = acc.id " +
            "left join account on b.user_account_id = account.id";
    public static final String GET_ORDER_BY_ID = GET_ALL_ORDERS +" WHERE b.id = ?";
    public static final String GET_ALL_ORDERS_BY_USER_ID = GET_ALL_ORDERS +" WHERE user_account_id  = ?";


    public static final String INSERT_PAYMENT = "INSERT INTO payment (order_value, payment_type, account_id, booking_id) VALUES (?, ?, ?, ?);";
    public static final String GET_ALL_SERVICES = "SELECT id, name FROM service";
    public static final String GET_AMOUNT_OF_ORDERS = "SELECT COUNT(*) AS count FROM booking;";
    public static final String FIND_ORDERS = "SELECT b.id, b.price, b.customer_comment, b.device, b.creating_time, " +
            "b.master_account_id, account.name AS customer_name, account.surname As customer_surname, " +
            "b.user_account_id, acc.name AS master_name, acc.surname AS master_surname, " +
            "b.order_status, s.id AS service_id, s.name from booking AS b LEFT JOIN booking_service AS b_s ON b_s.booking_id = b.id" +
            " LEFT JOIN service AS s ON s.id = b_s.service_id left join account as acc on b.master_account_id = acc.id " +
            "left join account on b.user_account_id = account.id LIMIT ?, ?";

}
