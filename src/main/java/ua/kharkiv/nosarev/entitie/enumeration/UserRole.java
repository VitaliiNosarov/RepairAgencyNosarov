package ua.kharkiv.nosarev.entitie.enumeration;

public enum UserRole {

    ADMIN(1), CUSTOMER(2), MASTER(3);

    private int id;

    UserRole(int id) {
        this.id = id;
    }
    public int getId(){
        return id;
    }
}
