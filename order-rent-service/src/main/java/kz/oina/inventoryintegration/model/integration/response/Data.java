package kz.oina.inventoryintegration.model.integration.response;

import java.util.UUID;

public record Data(UUID id,
                   UUID toyId,
                   String status) {
}
