package kz.oina.order.exceptions;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public static OrderNotFoundException notFound(UUID orderId) {
        return new OrderNotFoundException("Pending order not found with id=" + orderId);
    }
}
