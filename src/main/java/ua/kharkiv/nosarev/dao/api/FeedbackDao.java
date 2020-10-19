package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.FeedBack;


public interface FeedbackDao {

    /**
     * Returns Feedback entity with the same orderId from Database
     * orderId should be >0
     *
     * @param orderId
     * @return Feedback with same orderId
     */
    FeedBack getFeedback(long orderId);

    /**
     * Insert not null Feedback to database and return boolean result
     *
     * @param feedBack not null
     * @return boolean result of operation
     */
    boolean saveFeedback(FeedBack feedBack);
}
