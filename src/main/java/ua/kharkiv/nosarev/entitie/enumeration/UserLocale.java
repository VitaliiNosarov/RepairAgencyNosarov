package ua.kharkiv.nosarev.entitie.enumeration;

public enum UserLocale {

    EN("en"), RU("ru");
    private String value;

    UserLocale(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
