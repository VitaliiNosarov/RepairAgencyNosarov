package ua.kharkiv.nosarev.dao;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.OfficeDao;
import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.exception.DatabaseException;
import ua.kharkiv.nosarev.util.DaoUtil;

import javax.sql.DataSource;
import java.sql.*;
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
                    service.setId(rs.getLong("en.service_id"));
                    service.setNameEn(rs.getString("en.name"));
                    service.setNameRu(rs.getString("ru.name"));
                    list.add(service);
                }
            }
        } catch (SQLException throwables) {
            LOGGER.error("Can't get all services from database", throwables);
            throw new DatabaseException();
        }
        return list;
    }

    @Override
    public boolean saveService(Service service) {
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statementRu = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQLConstant.INSERT_SERVICE_EN, Statement.RETURN_GENERATED_KEYS);
            statementRu = connection.prepareStatement(SQLConstant.INSERT_SERVICE_RU);
            statement.setLong(1, service.getId());
            statement.setString(2, service.getNameEn());
            statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                service.setId(rs.getInt(1));
            }
            statementRu.setLong(1, service.getId());
            statementRu.setString(2, service.getNameRu());
            statementRu.executeUpdate();
            connection.commit();
            result = true;
            DaoUtil.rollBack(connection);
        } catch (SQLException ex) {
            DaoUtil.rollBack(connection);
            LOGGER.error("Can't save Service to database", ex);
            throw new DatabaseException();
        } finally {
            DaoUtil.close(rs, statementRu, statement, connection);
        }
        return result;
    }
}
