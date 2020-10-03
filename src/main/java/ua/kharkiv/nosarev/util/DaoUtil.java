package ua.kharkiv.nosarev.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public final class DaoUtil {

    private static final Logger LOGGER = Logger.getLogger(DaoUtil.class);

    private DaoUtil() {
    }

    public static void close(AutoCloseable... resources) {

        for (AutoCloseable resource : resources) {
            try {
                if (resource != null) {
                    resource.close();
                }
            } catch (Exception e) {
                LOGGER.error("Can't close resource ", e);
            }
        }
    }


    public static void rollBack(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException throwables) {
            LOGGER.error("Exception in connection rollback", throwables);
        }
    }
}
