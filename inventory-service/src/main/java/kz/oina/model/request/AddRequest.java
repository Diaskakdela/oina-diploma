package kz.oina.model.request;

import java.util.UUID;

public record AddRequest(UUID toyId,
                         Integer count,
                         String location) {
}
