package kz.oina.order.model;

import java.util.UUID;

public record OrderCreationParam(UUID renterId, UUID toyId, int count) {
}
