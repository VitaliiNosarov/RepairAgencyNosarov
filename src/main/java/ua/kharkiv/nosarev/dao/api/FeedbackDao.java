package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.FeedBack;


public interface FeedbackDao {

    /**
     *
     * @param orderId
     * @return
     */
    FeedBack getFeedback(long orderId);

    /**
     *
     * @param feedBack
     * @return
     */
    boolean saveFeedback(FeedBack feedBack);
}
