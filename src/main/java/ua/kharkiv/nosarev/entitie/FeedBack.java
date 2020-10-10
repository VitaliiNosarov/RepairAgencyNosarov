package ua.kharkiv.nosarev.entitie;

import ua.kharkiv.nosarev.entitie.enumeration.FeedbackRate;

import java.sql.Timestamp;

public class FeedBack extends AbstractEntity{

    private String comment;
    private Timestamp time;
    private FeedbackRate rate;
}
