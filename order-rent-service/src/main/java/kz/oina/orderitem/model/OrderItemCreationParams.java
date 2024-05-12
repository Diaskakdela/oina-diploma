package kz.oina.orderitem.model;

import java.util.UUID;

public record OrderItemCreationParams(UUID toyId,
                                      int count,
                                      UUID orderId,
                                      UUID renterId) {
}
