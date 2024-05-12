package kz.oina.order.web.request;

import java.util.UUID;

public record OrderCreationRequest(UUID renterId, UUID toyId, int count) {
}
