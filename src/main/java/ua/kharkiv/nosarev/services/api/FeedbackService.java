package ua.kharkiv.nosarev.services.api;

import ua.kharkiv.nosarev.entitie.FeedBack;

public interface FeedbackService {

    FeedBack getFeedback(long orderId);

    String saveFeedback(FeedBack feedBack);
}
