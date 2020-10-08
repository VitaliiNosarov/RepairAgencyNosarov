package ua.kharkiv.nosarev.entitie;

public class Service extends AbstractEntity {


    private String name;

    public Service() {
    }

    public Service(int id, String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
