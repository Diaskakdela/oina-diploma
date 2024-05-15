package kz.oina.model.response;

import java.util.UUID;

public record ToyAvailabilityResponse(UUID toyId,
                                      int count) {
}
