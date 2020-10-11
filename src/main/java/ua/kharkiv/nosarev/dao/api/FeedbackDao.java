package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.FeedBack;

public interface FeedbackDao {

    FeedBack getFeedback(long orderId);

    boolean saveFeedback(FeedBack feedBack);
}
