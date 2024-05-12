package kz.oina.exceptions.item;

import java.util.UUID;

public class ItemNotFoundException extends ItemException {

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(UUID toyId) {
        super("No inventory found for toy with id=" + toyId);
    }

    public static ItemNotFoundException notFoundByToyId(UUID toyId) {
        return new ItemNotFoundException("No inventory found for toy with id=" + toyId);
    }

    public static ItemNotFoundException notFoundByInventoryItemId(UUID inventoryItemId) {
        return new ItemNotFoundException("No inventory items found for id:" + inventoryItemId);
    }
}
