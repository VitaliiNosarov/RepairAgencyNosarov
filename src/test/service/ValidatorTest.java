package service;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.nosarev.entitie.FeedBack;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.FeedbackRate;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ua.kharkiv.nosarev.service.Validator.*;

public class ValidatorTest {

    private User user;
    private Order order;

    @Before
    public void init() {
        user = new User();
        user.setEmail("User@email.com");
        user.setPassword("Password1");
        user.setPhone("0950531870");
        user.setName("User");
        user.setSurName("UserSurname");
        user.setLocale(UserLocale.RU);

        order = new Order();
        order.setCustomerId(1);
        order.setStatus(OrderStatus.PAID);
        order.setDevice("Some Device");
        order.setComment("Comment comment");
    }

    @Test
    public void validateEmailShouldReturnFalseForWrongEmail() {
        String wrongEmail1 = "email@com";
        String wrongEmail2 = "emailcom.com";
        String wrongEmail3 = "email@";
        String wrongEmail4 = "@email";
        assertFalse(validateEmail(wrongEmail1));
        assertFalse(validateEmail(wrongEmail2));
        assertFalse(validateEmail(wrongEmail3));
        assertFalse(validateEmail(wrongEmail4));

    }

    @Test
    public void validateEmailShouldReturnTrueForValidEmail() {
        String correctEmail1 = "email@com.ua";
        String correctEmail2 = "e@com.ua";
        assertTrue(validateEmail(correctEmail1));
        assertTrue(validateEmail(correctEmail2));
    }

    @Test
    public void validatePasswordShouldReturnFalseForWrongPass() {
        String wrongPassword1 = "password";
        String wrongPassword2 = "Password";
        String wrongPassword3 = "Passw";
        String wrongPassword4 = "=@*....";
        assertFalse(validatePassword(wrongPassword1));
        assertFalse(validatePassword(wrongPassword2));
        assertFalse(validatePassword(wrongPassword3));
        assertFalse(validatePassword(wrongPassword4));
    }

    @Test
    public void validatePasswordShouldReturnTrueForCorrectPass() {
        String correctPassword1 = "Password1";
        String correctPassword2 = "password1";
        String correctPassword3 = "1Passwo2Rd";
        String correctPassword4 = "=@*..d1.";
        assertTrue(validatePassword(correctPassword1));
        assertTrue(validatePassword(correctPassword2));
        assertTrue(validatePassword(correctPassword3));
        assertTrue(validatePassword(correctPassword4));
    }

    @Test
    public void validatePhoneShouldReturnFalseForWrongNumber() {
        String wrongPhone1 = "09503";
        String wrongPhone2 = "phone";
        String wrongPhone3 = "095030950531863016";
        String wrongPhone4 = "087789ds098";
        assertFalse(validatePhone(wrongPhone1));
        assertFalse(validatePhone(wrongPhone2));
        assertFalse(validatePhone(wrongPhone3));
        assertFalse(validatePhone(wrongPhone4));
    }

    @Test
    public void validateUserShouldReturnFalseForUserWithWrongName() {
        user.setName("1");
        assertFalse(validateUser(user));
    }

    @Test
    public void validateUserShouldReturnFalseForUserWithWrongSurname() {
        user.setSurName("1");
        assertFalse(validateUser(user));
    }

    @Test
    public void validateUserShouldReturnTrueWithCorrectUser() {
        assertTrue(validateUser(user));
    }

    @Test
    public void validateOrderShouldReturnFalseWithWrongDevice() {
        order.setDevice("dev1");
        assertFalse(validateOrder(order));
    }

    @Test
    public void validateOrderShouldReturnFalseWithWrongComment() {
        order.setComment("Comment");
        assertFalse(validateOrder(order));
    }

    @Test
    public void validateOrderShouldReturnTrueWithCorrectOrder() {
        assertTrue(validateOrder(order));
    }

    @Test
    public void validateFeedbackShouldReturnFalseWithCorrectComment() {
        String wrongComment = "Comment";
        FeedBack feedBack = new FeedBack();
        feedBack.setRate(FeedbackRate.BAD);
        feedBack.setComment(wrongComment);
        assertTrue(validateFeedback(feedBack));
    }
}