package kz.oina.order.service.impl;

import kz.oina.order.entity.Order;
import kz.oina.order.entity.OrderStatus;
import kz.oina.order.exceptions.OrderNotFoundException;
import kz.oina.order.factory.OrderFactory;
import kz.oina.order.model.OrderCreationParam;
import kz.oina.order.model.OrderDetails;
import kz.oina.order.model.OrderWithOrderItems;
import kz.oina.order.repository.OrderRepository;
import kz.oina.order.service.OrderService;
import kz.oina.orderitem.entity.OrderItem;
import kz.oina.orderitem.model.OrderItemCreationParams;
import kz.oina.orderitem.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Log4j2
public class DefaultOrderService implements OrderService {

    @Value("${order.auto-cancel-minutes}")
    private int minutes;

    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

    @Override
    @Transactional
    public OrderWithOrderItems createOrder(OrderCreationParam orderCreationParam) {
        var toyId = orderCreationParam.toyId();
        var count = orderCreationParam.count();
        var renterId = orderCreationParam.renterId();
        var order = orderFactory.createOrder(new OrderDetails(renterId));

        var savedOrder = orderRepository.save(order);

        var orderItems = orderItemService.createOrderItem(new OrderItemCreationParams(toyId, count, order.getId(), renterId));
        log.info("Created order items: {}", orderItems);
        return new OrderWithOrderItems(savedOrder, orderItems);
    }

    @Override
    @Transactional
    public Order cancelOrder(UUID orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> OrderNotFoundException.notFound(orderId));
        order.cancelOrder();
        Collection<OrderItem> orderItems = orderItemService.cancelOrderItemsByOrderId(order.getId());
        log.info("Canceled order items: {}", orderItems);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order payOrder(UUID orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> OrderNotFoundException.notFound(orderId));
        order.payOrder();
        return orderRepository.save(order);
    }

    @Override
    public void cancelUnpaidOrders() {
        var cancelledOrders = orderRepository.findOrdersByStatusAndUpdatedBefore(OrderStatus.PENDING, LocalDateTime.now().minusMinutes(minutes))
                .stream()
                .map(Order::getId)
                .map(this::cancelOrder)
                .map(Order::getId)
                .toList();
        log.info("Cancelled unpaid orders. ids={}", cancelledOrders);
    }

    @Override
    public Order rentOrder(UUID orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> OrderNotFoundException.notFound(orderId));
        order.rentOrder();
        Collection<OrderItem> orderItems = orderItemService.rentOrderItems(order.getId());
        log.info("Rent order items: {}", orderItems);
        return orderRepository.save(order);
    }
}
