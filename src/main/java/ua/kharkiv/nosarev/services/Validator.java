package ua.kharkiv.nosarev.services;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String EMAIL_PATTERN = "[a-zA-Z]+@{1}[a-zA-Z]+\\.{1}[a-zA-Z]{2,4}";
    private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{5,}$";
    private static final String PHONE_PATTERN = "[+]{0,1}\\d{6,12}";

    private Validator() {
    }

    public static boolean validateEmail(String email) {
        if (email == null) {
            return false;
        }
        Pattern patternMail = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = patternMail.matcher(email);
        return matcher.find();
    }

    public static boolean validatePassword(String password) {
        if (password == null) {
            return false;
        }
        Pattern patternMail = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = patternMail.matcher(password);
        return matcher.find();
    }

    public static boolean validatePhone(String phone) {
        if (phone == null) {
            return false;
        }
        Pattern patternMail = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = patternMail.matcher(phone);
        return matcher.find();
    }

    public static boolean validateUser(User user) {
        if (user == null) {
            return false;
        }
        if (user.getName() == null || user.getSurName() == null || user.getEmail() == null
                || user.getPassword() == null || user.getPhone() == null || user.getLocale() == null) {
            return false;
        }
        return validateEmail(user.getEmail()) && validatePassword(user.getPassword()) && validatePhone(user.getPhone())
                && user.getName().length() >= 2 && user.getSurName().length() >= 2;
    }

    public static boolean validateOrder(Order order) {
        if (order == null) {
            return false;
        }
        if (order.getDevice().length() > 60 && order.getDevice().length() < 5) {
            return false;
        }
        if (order.getComment().length() > 1000&&order.getComment().length() <10) {
            return false;
        }

        return true;
    }
}
