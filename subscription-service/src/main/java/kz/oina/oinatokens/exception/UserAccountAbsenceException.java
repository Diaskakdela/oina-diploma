package kz.oina.oinatokens.exception;

public class UserAccountAbsenceException extends RuntimeException {
    public UserAccountAbsenceException() {
    }

    public UserAccountAbsenceException(String message) {
        super(message);
    }

    public UserAccountAbsenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
