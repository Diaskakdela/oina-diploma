package kz.oina.model.request;

import java.util.UUID;

public record CancelReserveRequest(UUID inventoryItemId) {
}
