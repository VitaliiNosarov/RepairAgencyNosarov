package ua.kharkiv.nosarev.entitie;

public class Service extends AbstractEntity {


    private String name;

    private String nameRu;

    public Service() {
    }

    public Service(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    @Override
    public String toString() {
        return name;
    }
}
