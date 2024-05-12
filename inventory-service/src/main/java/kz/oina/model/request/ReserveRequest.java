package kz.oina.model.request;

import java.util.UUID;

public record ReserveRequest(UUID toyId, int count) {
}
