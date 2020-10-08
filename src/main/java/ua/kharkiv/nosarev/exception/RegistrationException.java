package ua.kharkiv.nosarev.exception;

public class RegistrationException extends RuntimeException {

    private String message;

    public RegistrationException(String message) {
        super();
        this.message = message;
    }
}
