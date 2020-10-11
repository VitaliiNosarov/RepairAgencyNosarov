package ua.kharkiv.nosarev.services;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.MessageType;
import ua.kharkiv.nosarev.dao.api.FeedbackDao;
import ua.kharkiv.nosarev.entitie.FeedBack;
import ua.kharkiv.nosarev.services.api.FeedbackService;

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
        boolean result = false;
        if (Validator.validateFeedback(feedBack)) {
            result = feedbackDao.saveFeedback(feedBack);
        } else {
            LOGGER.warn("Service exception in saveFeedback, wrong fields");
        }
        if (result) {
            return MessageType.ADD_FEEDBACK.getMessage();
        }
        return MessageType.WRONG_FIELDS.getMessage();
    }
}
