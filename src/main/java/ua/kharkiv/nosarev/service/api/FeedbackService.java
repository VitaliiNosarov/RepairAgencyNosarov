package ua.kharkiv.nosarev.service.api;

import ua.kharkiv.nosarev.entitie.FeedBack;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;

public interface FeedbackService {

    FeedBack getFeedback(long orderId);

    InfoMessage saveFeedback(FeedBack feedBack);
}
