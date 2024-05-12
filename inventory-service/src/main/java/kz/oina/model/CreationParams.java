package kz.oina.model;

import java.util.UUID;

public record CreationParams(UUID toyId,
                             Integer count,
                             String location) {
}
