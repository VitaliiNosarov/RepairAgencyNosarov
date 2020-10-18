package ua.kharkiv.nosarev.dao;

public class SQLConstant {

    public static final String GET_USER_BY_EMAIL_PASS = "SELECT * FROM account where email = ?;";
    public static final String GET_USER_BY_ID = "SELECT * FROM account where id = ?;";
    public static final String DELETE_USER_BY_EMAIL = "DELETE FROM account WHERE id = ?";
    public static final String FIND_USERS = "SELECT * FROM account LIMIT ?, ?";
    public static final String GET_ALL_USERS_BY_ROLE = "SELECT * FROM account WHERE role = ?";
    public static final String GET_ROLE_BY_USER_ID = "select role from account where id = ?";
    public static final String GET_AMOUNT_OF_USERS = "SELECT COUNT(*) AS count FROM account ";
    public static final String SAVE_USER = "INSERT INTO account (id, email, password, name, surname, role, phone, locale, balance)" +
            " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
            " AS new on duplicate key update id = new.id, email=new.email, password=new.password, name=new.name," +
            " surname=new.surname, role = new.role, phone=new.phone, locale = new.locale, balance = new.balance;";
    public static final String DELETE_ORDER_BY_ID = "DELETE FROM order WHERE id = ?";
    public static final String INSERT_ORDER = "INSERT INTO booking (user_account_id, device, customer_comment) VALUES (?, ?, ?);";
    public static final String INSERT_SERVICES_TO_ORDER = "INSERT INTO booking_service (booking_id, service_id) VALUES (?, ?);";
    public static final String UPDATE_ORDER = "UPDATE booking SET price = ?, master_account_id = ?, order_status = ? WHERE id = ?;";
    public static final String FIND_ORDERS = "SELECT b.id, b.price, b.customer_comment, b.device, b.creating_time, " +
            "b.master_account_id, account.name AS customer_name, account.surname As customer_surname, " +
            "b.user_account_id, acc.name AS master_name, acc.surname AS master_surname, b.order_status, " +
            "group_concat(Distinct s.service_id, '/', s.name SEPARATOR '&') AS services FROM booking AS b " +
            "LEFT JOIN booking_service AS b_s ON b_s.booking_id = b.id " +
            "LEFT JOIN service_en AS s ON s.service_id = b_s.service_id " +
            "LEFT JOIN account as acc on b.master_account_id = acc.id " +
            "LEFT JOIN account on b.user_account_id = account.id ";
    public static final String GET_ORDER_BY_ID = FIND_ORDERS + " WHERE b.id = ? GROUP BY id";
    public static final String GET_ALL_ORDERS_BY_USER_ID = FIND_ORDERS + " WHERE user_account_id  = ? GROUP BY id";
    public static final String GET_ALL_ORDERS = FIND_ORDERS + " GROUP BY id";
    public static final String GET_FEEDBACK = "SELECT booking_id, feedback_text, creating_time, rate FROM feedback WHERE booking_id = ?";
    public static final String SAVE_FEEDBACK = "INSERT INTO feedback (booking_id, feedback_text, rate) VALUES (?, ?, ?)";
    public static final String GET_ALL_SERVICES = "SELECT service_id, name FROM service_%s";
    public static final String INSERT_SERVICE_EN = "INSERT INTO service_en (service_id, name)\n" +
            "VALUES (? ,?) AS new on duplicate key update service_id = new.service_id, \n" +
            " name=new.name;";
    public static final String INSERT_SERVICE_RU = "INSERT INTO service_ru (service_id, name)\n" +
            "VALUES (?,?) AS new on duplicate key update service_id = new.service_id, \n" +
            " name=new.name;";
    public static final String GET_AMOUNT_OF_ORDERS = "SELECT COUNT(*) AS count FROM booking ";
    public static final String GET_AMOUNT_OF_NEW_ORDERS = "SELECT COUNT(*) AS count FROM booking WHERE order_status = 'WAITING_FOR_PROCESSING'";
    public static final String GET_PAYMENT = "SELECT payment_value, creating_time, account_id FROM payment WHERE booking_id = ? ";
    public static final String SAVE_PAYMENT = "INSERT INTO payment (payment_value, account_id, booking_id) VALUES (?, ?, ?) ";
    public static final String SET_ORDER_STATUS = "UPDATE booking SET order_status = ? WHERE id = ? ";
    public static final String GET_ACCOUNT_BALANCE = "SELECT balance FROM account WHERE id = ?";
    public static final String SET_ACCOUNT_BALANCE = "UPDATE account SET balance = ? WHERE id = ?";

    private SQLConstant() {
    }

}
