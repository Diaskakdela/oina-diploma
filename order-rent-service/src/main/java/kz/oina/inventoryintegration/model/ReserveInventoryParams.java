package kz.oina.inventoryintegration.model;

import java.util.UUID;

public record ReserveInventoryParams(UUID toyId, int count) {
}
