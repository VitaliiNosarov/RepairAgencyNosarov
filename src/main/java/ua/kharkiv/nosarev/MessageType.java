package ua.kharkiv.nosarev;

public enum MessageType {

    REGISTRATION("Registration success"),
    WRONG_REGISTRATION("Rejected. User with current email is already registered"),
    UPDATING_ORDER("Order was successfully updated"),
    UPDATING_ACCOUNT("Account was successfully updated"),
    CREATING_FEEDBACK("Feedback was was successfully updated"),
    CREATING_ORDER("Order was successfully saved"),
    ADD_FEEDBACK("Feedback was saved"),
    WRONG_FIELDS("Denied. Wrong fields"),
    PAYMENT_SUCCESS("Order was successfully paid"),
    PAYMENT_DENIED("Not enough money on your balance. Contact the manager for top up"),
    WRONG_AUTHENTICATION("Wrong login or password");
    private String message;

    public String getMessage() {
        return message;
    }

    MessageType(String message) {
        this.message = message;
    }
}
