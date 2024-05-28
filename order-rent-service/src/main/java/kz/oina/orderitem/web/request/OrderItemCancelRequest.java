package kz.oina.orderitem.web.request;

import java.util.UUID;

public record OrderItemCancelRequest(UUID orderItemId) {
}
