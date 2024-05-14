package kz.oina.orderitem.service;

import kz.oina.orderitem.entity.OrderItem;
import kz.oina.orderitem.model.OrderItemCreationParams;

import java.util.Collection;
import java.util.UUID;

public interface OrderItemService {

    Collection<OrderItem> createOrderItem(OrderItemCreationParams creationParams);

    Collection<OrderItem> rentOrderItems(UUID orderId);

    OrderItem returnOrderItem(UUID orderItemId);

    Collection<OrderItem> cancelOrderItemsByOrderId(UUID orderId);

    Collection<OrderItem> findOrderItemsByOrderId(UUID orderId);

    void cancelOrderItemById(UUID orderItemId);

    Collection<OrderItem> findActiveOrderItemsByRenterId(UUID renterId);

    Collection<OrderItem> findCompleted(UUID renterId);

    Collection<OrderItem> findPending(UUID renterId);
    Collection<OrderItem> findPendingByOrderId(UUID orderID);

}
