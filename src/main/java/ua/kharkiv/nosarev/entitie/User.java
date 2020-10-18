package ua.kharkiv.nosarev.entitie;

import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;

import java.io.Serializable;
import java.math.BigDecimal;

public class User extends AbstractEntity implements Serializable {


    private String email;
    private String password;
    private String name;
    private String surName;
    private String phone;
    private UserRole role;
    private BigDecimal balance;
    private UserLocale locale;

    public User() {
        role = UserRole.CUSTOMER;
        locale = UserLocale.EN;
        balance = new BigDecimal(0);
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal ballance) {
        this.balance = ballance;
    }

    public UserLocale getLocale() {
        return locale;
    }

    public void setLocale(UserLocale locale) {
        this.locale = locale;
    }
}
