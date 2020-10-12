package ua.kharkiv.nosarev.dao;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.ServiceDao;
import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.exception.DatabaseException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoImpl implements ServiceDao {

    private static final Logger LOGGER = Logger.getLogger(ServiceDaoImpl.class);
    private DataSource dataSource;

    public ServiceDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Service> getAllServices() {
        List<Service> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(SQLConstant.GET_ALL_SERVICES)) {
                while (rs.next()) {
                    Service service = new Service();
                    service.setId(rs.getInt("id"));
                    service.setName(rs.getString("name"));
                    list.add(service);
                }
            }
        } catch (SQLException throwables) {
            LOGGER.error("Can't get all services from database", throwables);
            throw new DatabaseException();
        }
        return list;
    }
}
