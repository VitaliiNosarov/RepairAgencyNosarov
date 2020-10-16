package ua.kharkiv.nosarev.service.api;

import ua.kharkiv.nosarev.entitie.FeedBack;

public interface FeedbackService {

    FeedBack getFeedback(long orderId);

    String saveFeedback(FeedBack feedBack);
}
