package ua.kharkiv.nosarev.service;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.nosarev.dao.api.FeedbackDao;
import ua.kharkiv.nosarev.entitie.FeedBack;
import ua.kharkiv.nosarev.entitie.enumeration.FeedbackRate;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;
import ua.kharkiv.nosarev.service.api.FeedbackService;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FeedbackServiceImplTest {

    private FeedbackDao feedbackDao;
    private Validator validator;
    private FeedbackService feedbackService;
    private FeedBack feedBack;

    @Before
    public void init() {
        feedbackDao = mock(FeedbackDao.class);
        validator = mock(Validator.class);
        feedbackService = new FeedbackServiceImpl(feedbackDao, validator);
        feedBack = new FeedBack();
        feedBack.setComment("Comment");
        feedBack.setRate(FeedbackRate.GREAT);
    }

    @Test
    public void getFeedbackShouldReturnFeedbackFromDao() {
        when(feedbackService.getFeedback(1L)).thenReturn(feedBack);
        feedbackService.getFeedback(1);
        assertEquals(feedBack, feedbackService.getFeedback(1));
    }

    @Test
    public void saveFeedbackShouldReturnSuccessMessage() {
        when(validator.validateFeedback(feedBack)).thenReturn(true);
        when(feedbackDao.saveFeedback(feedBack)).thenReturn(true);
        assertEquals(InfoMessage.ADD_FEEDBACK_SUCCESS, feedbackService.saveFeedback(feedBack));
    }

    @Test
    public void saveFeedbackShouldReturnWrongFieldsMessageWhenIsNotValid() {
        when(validator.validateFeedback(feedBack)).thenReturn(false);
        assertEquals(InfoMessage.WRONG_FIELDS, feedbackService.saveFeedback(feedBack));
    }

    @Test
    public void saveFeedbackShouldReturnWrongFieldsMessageWhenDaoReturnFalse() {
        when(validator.validateFeedback(feedBack)).thenReturn(true);
        when(feedbackDao.saveFeedback(feedBack)).thenReturn(false);
        assertEquals(InfoMessage.WRONG_FIELDS, feedbackService.saveFeedback(feedBack));
    }

}