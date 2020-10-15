package ua.kharkiv.nosarev.dao;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.OfficeDao;
import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.exception.DatabaseException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OfficeDaoImpl implements OfficeDao {

    private static final Logger LOGGER = Logger.getLogger(OfficeDaoImpl.class);
    private DataSource dataSource;

    public OfficeDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Service> getAllServices(UserLocale locale) {
        List<Service> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            String query = String.format(SQLConstant.GET_ALL_SERVICES, locale.toString());
            try (ResultSet rs = statement.executeQuery(query)) {
                while (rs.next()) {
                    Service service = new Service();
                    service.setId(rs.getLong("service_id"));
                    service.setName(rs.getString("name"));
                    list.add(service);
                }
            }
        } catch (SQLException throwables) {
            LOGGER.error("Can't get all RU services from database", throwables);
            throw new DatabaseException();
        }
        return list;
    }
}
