package ua.kharkiv.nosarev.service;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.FeedbackDao;
import ua.kharkiv.nosarev.entitie.FeedBack;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;
import ua.kharkiv.nosarev.service.api.FeedbackService;

public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger LOGGER = Logger.getLogger(FeedbackServiceImpl.class);
    private FeedbackDao feedbackDao;

    public FeedbackServiceImpl(FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    @Override
    public FeedBack getFeedback(long orderId) {
        return feedbackDao.getFeedback(orderId);
    }

    @Override
    public String saveFeedback(FeedBack feedBack) {
        if (Validator.validateFeedback(feedBack)) {
            if (feedbackDao.saveFeedback(feedBack)) {
                return InfoMessage.ADD_FEEDBACK_SUCCESS.toString();
            }
        }
        LOGGER.warn("Service exception in saveFeedback, wrong fields");
        return InfoMessage.WRONG_FIELDS.toString();

    }
}
