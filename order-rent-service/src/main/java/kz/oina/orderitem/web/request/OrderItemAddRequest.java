package kz.oina.orderitem.web.request;

import java.util.UUID;

public record OrderItemAddRequest(UUID toyId,
                                  int count,
                                  UUID orderId,
                                  UUID renterId) {
}
