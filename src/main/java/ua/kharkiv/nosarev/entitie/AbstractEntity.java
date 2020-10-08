package ua.kharkiv.nosarev.entitie;

public abstract class AbstractEntity {

    private long id;

    public AbstractEntity() {
        id =0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
