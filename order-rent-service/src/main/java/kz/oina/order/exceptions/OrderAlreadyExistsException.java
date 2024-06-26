package kz.oina.order.exceptions;

public class OrderAlreadyExistsException extends RuntimeException {
    public OrderAlreadyExistsException(String message) {
        super(message);
    }

    public OrderAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
