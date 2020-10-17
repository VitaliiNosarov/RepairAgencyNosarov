package ua.kharkiv.nosarev.dao;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.nosarev.dao.api.FeedbackDao;
import ua.kharkiv.nosarev.entitie.enumeration.FeedbackRate;

import javax.sql.DataSource;

import static org.junit.Assert.*;

public class FeedbackDaoImplTest {

    private DataSource dataSource;
    private FeedbackDao feedbackDao;

    @Before
    public void setUp() throws Exception {
        dataSource = DBCPDataSource.getDataSource();
        feedbackDao = new FeedbackDaoImpl(dataSource);
    }

    @Test
    public void getFeedbackShouldReturnFeedbackByOrderId() {
      assertEquals(FeedbackRate.GREAT, feedbackDao.getFeedback(1).getRate());
    }
}