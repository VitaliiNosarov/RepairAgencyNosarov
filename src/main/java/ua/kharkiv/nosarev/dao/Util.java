package ua.kharkiv.nosarev.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class Util {

    static final Logger LOGGER = Logger.getLogger(Util.class);

    public static void close(AutoCloseable... resources) {
        try {
            Arrays.stream(resources).forEach(i -> close(i));
        } catch (Exception e) {
            LOGGER.error("Can't close resource ", e);
        }
    }

    public static void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException throwables) {
            LOGGER.error("Exception in connection rollback", throwables);
        }
    }
}
