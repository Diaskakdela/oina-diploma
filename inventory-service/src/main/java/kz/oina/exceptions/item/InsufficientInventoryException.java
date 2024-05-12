package kz.oina.exceptions.item;

public class InsufficientInventoryException extends ItemException {
    public InsufficientInventoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientInventoryException(String message) {
        super(message);
    }
}
