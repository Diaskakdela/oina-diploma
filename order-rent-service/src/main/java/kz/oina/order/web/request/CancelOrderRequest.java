package kz.oina.order.web.request;

import java.util.UUID;

public record CancelOrderRequest(UUID orderId) {
}
