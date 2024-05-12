package kz.oina.orderitem.web.response;

import java.util.UUID;

public record OrderItemDTO(UUID id,
                           UUID toyId,
                           String status) {
}
