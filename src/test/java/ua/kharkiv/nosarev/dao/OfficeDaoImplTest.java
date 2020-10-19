package ua.kharkiv.nosarev.dao;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.nosarev.dao.api.OfficeDao;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;

public class OfficeDaoImplTest {

    private DataSource dataSource;
    private OfficeDao officeDao;

    @Before
    public void setUp() throws Exception {
        dataSource = DBCPDataSource.getDataSource();
        officeDao = new OfficeDaoImpl(dataSource);
    }

    @Test
    public void getAllServicesShouldReturnAllEnServicesFromDB() {
        assertEquals(2, officeDao.getAllServices().size());
    }

}