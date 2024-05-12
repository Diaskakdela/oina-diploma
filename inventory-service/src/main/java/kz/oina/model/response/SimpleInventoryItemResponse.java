package kz.oina.model.response;

import kz.oina.entity.InventoryStatus;

import java.util.UUID;

public record SimpleInventoryItemResponse(UUID id,
                                          UUID toyId,
                                          InventoryStatus status) {
}
