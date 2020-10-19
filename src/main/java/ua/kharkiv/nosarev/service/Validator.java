package ua.kharkiv.nosarev.service;

import ua.kharkiv.nosarev.entitie.FeedBack;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.entitie.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String EMAIL_PATTERN = "[a-zA-Z]+@{1}[a-zA-Z]+\\.{1}[a-zA-Z]{2,4}";
    private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.)(?=.*[a-zA-Z]).{5,}$";
    private static final String PHONE_PATTERN = "(^[0-9]{6,12})[^a-zA-Zа-яА-Я]|^$";

    public boolean validateEmail(String email) {
        if (email == null || email.length() < 5 || email.length() > 25) {
            return false;
        }
        Pattern patternMail = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = patternMail.matcher(email);
        return matcher.find();
    }

    public boolean validatePassword(String password) {
        if (password == null) {
            return false;
        }
        if (password.length() < 5 || password.length() > 128) {
            return false;
        }
        Pattern patternMail = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = patternMail.matcher(password);
        return matcher.find();
    }

    public boolean validatePhone(String phone) {
        if (phone == null) {
            return false;
        }
        if (phone.length() < 6 || phone.length() > 12) {
            return false;
        }
        Pattern patternMail = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = patternMail.matcher(phone);
        return matcher.find();
    }

    public boolean validateUser(User user) {
        if (user == null) {
            return false;
        }
        if (user.getName() == null || user.getSurName() == null || user.getEmail() == null
                || user.getPassword() == null || user.getPhone() == null || user.getLocale() == null) {
            return false;
        }
        if (user.getName().length() < 2 || user.getName().length() > 30) {
            return false;
        }
        if (user.getSurName().length() < 2 || user.getSurName().length() > 35) {
            return false;
        }
        return validateEmail(user.getEmail()) && validatePassword(user.getPassword()) && validatePhone(user.getPhone())
                && user.getName().length() >= 2 && user.getSurName().length() >= 2;
    }

    public boolean validateOrder(Order order) {
        if (order == null) {
            return false;
        }
        if (order.getDevice() == null || order.getDevice().length() < 5
                || order.getDevice().length() > 60) {
            return false;
        }
        if (order.getComment() == null || order.getComment().length() < 10
                || order.getComment().length() > 200) {
            return false;
        }
        return true;
    }

    public boolean validateFeedback(FeedBack feedBack) {
        if (feedBack == null) {
            return false;
        }
        if (feedBack.getComment() == null || feedBack.getComment().length() > 300) {
            return false;
        }
        return feedBack.getRate() != null;
    }

    public boolean validateService(Service service) {
        if (service == null) {
            return false;
        }
        if (service.getNameEn() == null || service.getNameRu() == null) {
            return false;
        }
        return service.getNameEn().length() > 4 && service.getNameRu().length() > 4;
    }
}
