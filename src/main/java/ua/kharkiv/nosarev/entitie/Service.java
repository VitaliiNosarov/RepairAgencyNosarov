package ua.kharkiv.nosarev.entitie;

public class Service extends AbstractEntity {


    private String nameEn;

    private String nameRu;

    public Service() {
    }

    public Service(int id, String nameEn) {
        super(id);
        this.nameEn = nameEn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    @Override
    public String toString() {
        return nameEn;
    }
}
