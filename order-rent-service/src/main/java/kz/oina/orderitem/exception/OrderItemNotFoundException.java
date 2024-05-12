package kz.oina.orderitem.exception;

import java.util.UUID;

public class OrderItemNotFoundException extends RuntimeException {
    public OrderItemNotFoundException(String message) {
        super(message);
    }

    public OrderItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public static OrderItemNotFoundException notFoundById(UUID orderItemId) {
        return new OrderItemNotFoundException("Order item with id " + orderItemId + " not found");
    }
}
