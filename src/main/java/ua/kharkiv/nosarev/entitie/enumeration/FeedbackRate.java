package ua.kharkiv.nosarev.entitie.enumeration;

public enum FeedbackRate {

    GREAT("Great"), NORMAL("Normal"), BAD("Bad");

    private String value;

    FeedbackRate(String value) {
        this.value = value;
    }
}
