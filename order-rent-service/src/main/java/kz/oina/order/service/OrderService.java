package kz.oina.order.service;

import kz.oina.order.entity.Order;
import kz.oina.order.model.OrderCreationParam;
import kz.oina.order.model.OrderWithOrderItems;

import java.util.UUID;

public interface OrderService {

    OrderWithOrderItems createOrder(OrderCreationParam orderCreationParam);

    Order cancelOrder(UUID orderId);

    Order payOrder(UUID orderId);

    void cancelUnpaidOrders();

    Order rentOrder(UUID orderId);
}
