package ua.kharkiv.nosarev.service.api;

import ua.kharkiv.nosarev.entitie.FeedBack;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;

public interface FeedbackService {

    /**
     * Return Feedback from Database with id equal to orderId
     *
     * @param orderId should be>0
     * @return Feedback from Database
     */
    FeedBack getFeedback(long orderId);

    /**
     * Validate feedback and save in Database. Returns InfoMessage as a result of operation
     * ADD_FEEDBACK_SUCCESS when feedback was successfully saved and WRONG_FIELDS when result of saving is false
     *
     * @return InfoMessage which inform about result of operation
     */
    InfoMessage saveFeedback(FeedBack feedBack);
}
