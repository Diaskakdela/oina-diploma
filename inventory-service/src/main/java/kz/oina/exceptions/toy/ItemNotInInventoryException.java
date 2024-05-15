package kz.oina.exceptions.toy;

import java.util.UUID;

public class ItemNotInInventoryException extends RuntimeException {
    public ItemNotInInventoryException(String message) {
        super(message);
    }

    public static ItemNotInInventoryException toyIdNotFound(UUID toyId) {
        return new ItemNotInInventoryException("toyId=" + toyId.toString() + " not exists in inventory");
    }
}
