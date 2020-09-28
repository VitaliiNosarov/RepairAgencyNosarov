package ua.kharkiv.nosarev.dao;

public class SQLConstant {

    public static final String GET_USER_BY_EMAIL_PASS = "SELECT id, name, surname, phone, email, password, balance, locale, role " +
            "FROM account where email = ?;";
    public static final String DELETE_USER_BY_EMAIL = "DELETE FROM account WHERE email = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM account";
    public static final String INSERT_USER = "INSERT INTO account (name, surname, phone, email, password, balance, locale, role) \n" +
            " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String GET_ORDER_BY_ID = "";  //TODO
    public static final String DELETE_ORDER_BY_ID = "DELETE FROM order WHERE id = ?";


}
