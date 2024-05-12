package kz.oina.inventoryintegration.model.integration.request;

import java.util.UUID;

public record ReserveInventoryIntegrationRequest(UUID toyId, int count) {
}
