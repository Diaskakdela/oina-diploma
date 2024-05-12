package kz.oina.order.web.response;

import kz.oina.orderitem.web.response.OrderItemDTO;

import java.util.Collection;

public record OrderWithOrderItemsDTO(OrderDTO order,
                                     Collection<OrderItemDTO> orderItems) {
}
