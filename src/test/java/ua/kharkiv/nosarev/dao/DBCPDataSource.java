package ua.kharkiv.nosarev.dao;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public final class DBCPDataSource {

    private static final BasicDataSource ds = new BasicDataSource();
    private static final String URL = "jdbc:mysql://localhost:3306/repair_agency_test?serverTimezone=UTC";

    static {
        ds.setUrl(URL);
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(20);
    }

    private DBCPDataSource() {
    }

    public static DataSource getDataSource() throws SQLException {
        return ds;
    }
}
