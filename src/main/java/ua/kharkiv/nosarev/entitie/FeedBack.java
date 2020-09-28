package ua.kharkiv.nosarev.entitie;

import java.time.LocalDateTime;

public class FeedBack{

    private int id;
    private String comment;
    private LocalDateTime time;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }


}
