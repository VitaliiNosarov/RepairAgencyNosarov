package ua.kharkiv.nosarev.dao;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.FeedbackDao;
import ua.kharkiv.nosarev.entitie.FeedBack;
import ua.kharkiv.nosarev.entitie.enumeration.FeedbackRate;
import ua.kharkiv.nosarev.exception.DatabaseException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackDaoImpl implements FeedbackDao {

    private static final Logger LOGGER = Logger.getLogger(FeedbackDaoImpl.class);
    private DataSource dataSource;

    public FeedbackDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public FeedBack getFeedback(long orderId) {
        FeedBack feedBack = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstant.GET_FEEDBACK)) {
            statement.setLong(1, orderId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String comment = rs.getString("feedback_text");
                    if (comment != null) {
                        feedBack = new FeedBack();
                        feedBack.setComment(comment);
                        feedBack.setRate(FeedbackRate.valueOf(rs.getString("rate")));
                        feedBack.setTime(rs.getTimestamp("creating_time"));
                    }
                }
            }
        } catch (SQLException throwables) {
            LOGGER.error("Can't get feedback from database", throwables);
        }
        return feedBack;
    }

    @Override
    public boolean saveFeedback(FeedBack feedBack) {
        boolean result = false;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstant.SAVE_FEEDBACK)) {
            if (feedBack != null) {
                statement.setLong(1, feedBack.getId());
                statement.setString(2, feedBack.getComment());
                statement.setString(3, feedBack.getRate().toString());
                result = statement.executeUpdate() > 0;
            }
        } catch (SQLException throwables) {
            LOGGER.error("Can't insert feedback to database");
            throw new DatabaseException();
        }
        return result;
    }
}
