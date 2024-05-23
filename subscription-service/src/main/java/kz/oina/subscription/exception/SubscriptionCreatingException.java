package kz.oina.subscription.exception;

public class SubscriptionCreatingException extends RuntimeException {
    public SubscriptionCreatingException(String message) {
        super(message);
    }

    public SubscriptionCreatingException(String message, Throwable cause) {
        super(message, cause);
    }
}
