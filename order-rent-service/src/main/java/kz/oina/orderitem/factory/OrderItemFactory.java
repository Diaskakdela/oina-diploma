package kz.oina.orderitem.factory;

import kz.oina.orderitem.entity.OrderItem;
import kz.oina.orderitem.model.OrderItemDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class OrderItemFactory {

    public Collection<OrderItem> createOrderItems(OrderItemDetails orderItemDetails) {
        return orderItemDetails.inventoryItems()
                .stream()
                .map(item -> new OrderItem(orderItemDetails.orderId(), item.id(), orderItemDetails.renterId(), item.toyId()))
                .toList();
    }
}
