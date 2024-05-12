package kz.oina.exceptions.item;

public class ItemException extends RuntimeException {
    public ItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemException(String message) {
        super(message);
    }
}
