package ua.kharkiv.nosarev.entitie;

import ua.kharkiv.nosarev.entitie.enumeration.FeedbackRate;

import java.sql.Timestamp;

public class FeedBack extends AbstractEntity {

    private String comment;
    private Timestamp time;
    private FeedbackRate rate;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public FeedbackRate getRate() {
        return rate;
    }

    public void setRate(FeedbackRate rate) {
        this.rate = rate;
    }
}
