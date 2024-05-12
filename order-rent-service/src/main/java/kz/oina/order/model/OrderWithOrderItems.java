package kz.oina.order.model;

import kz.oina.order.entity.Order;
import kz.oina.orderitem.entity.OrderItem;

import java.util.Collection;

public record OrderWithOrderItems(Order order,
                                  Collection<OrderItem> orderItems) {

}
