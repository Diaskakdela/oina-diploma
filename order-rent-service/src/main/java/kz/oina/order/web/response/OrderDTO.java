package kz.oina.order.web.response;

import kz.oina.order.entity.Order;

import java.util.UUID;

public record OrderDTO(UUID id,
                       UUID renterId,
                       String status) {
    public static OrderDTO from(Order order) {
        return new OrderDTO(order.getId(), order.getRenterId(), order.getStatus().name());
    }
}
