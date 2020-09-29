package ua.kharkiv.nosarev.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public final class Util {

    private static final Logger LOGGER = Logger.getLogger(Util.class);

    private Util() {
    }

    public static void close(AutoCloseable... resources) {
        try {
            for (AutoCloseable resource : resources) {
                if (resource != null) resource.close();
            }
        } catch (Exception e) {
            LOGGER.error("Can't close resource ", e);
        }
    }

    public static void rollBack(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException throwables) {
            LOGGER.error("Exception in connection rollback", throwables);
        }
    }
}
