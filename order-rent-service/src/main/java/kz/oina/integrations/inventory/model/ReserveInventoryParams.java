package kz.oina.integrations.inventory.model;

import java.util.UUID;

public record ReserveInventoryParams(UUID toyId, int count) {
}
