package kz.oina.order.exceptions;

public class OrderCreatingException extends RuntimeException {
    public OrderCreatingException(String message, Throwable cause) {
        super(message, cause);
    }
}
