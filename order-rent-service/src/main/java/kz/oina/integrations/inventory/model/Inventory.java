package kz.oina.integrations.inventory.model;

import java.util.UUID;

public record Inventory(UUID id,
                        UUID toyId,
                        String status) {
}
