package kz.oina.orderitem.service;

import kz.oina.inventoryintegration.model.ReserveInventoryParams;
import kz.oina.inventoryintegration.service.InventoryIntegrationService;
import kz.oina.order.exceptions.OrderCreatingException;
import kz.oina.orderitem.entity.OrderItem;
import kz.oina.orderitem.entity.RentalStatus;
import kz.oina.orderitem.exception.OrderItemNotFoundException;
import kz.oina.orderitem.factory.OrderItemFactory;
import kz.oina.orderitem.model.OrderItemCreationParams;
import kz.oina.orderitem.model.OrderItemDetails;
import kz.oina.orderitem.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Log4j2
public class DefaultOrderItemService implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemFactory orderItemFactory;
    private final InventoryIntegrationService inventoryIntegrationService;

    @Override
    public Collection<OrderItem> createOrderItem(OrderItemCreationParams creationParams) {
        var orderId = creationParams.orderId();
        log.info("Create order items for order with id={}", orderId);
        var inventoryItems = inventoryIntegrationService.reserveInventory(new ReserveInventoryParams(creationParams.toyId(), creationParams.count()));
        var createdOrderItems = orderItemFactory.createOrderItems(new OrderItemDetails(inventoryItems, orderId, creationParams.renterId()));
        log.info("Created order items for order with id={}. Created order items={}", orderId, createdOrderItems);
        try {
            return orderItemRepository.saveAll(createdOrderItems);
        } catch (Exception e) {
            log.error("Error while creating orderItems. {}", creationParams, e);
            inventoryIntegrationService.cancelReserve(inventoryItems);
            throw new OrderCreatingException("Error while creating order item", e);
        }
    }

    @Override
    public Collection<OrderItem> rentOrderItems(UUID orderId) {
        log.info("Renting orders for orderId={}", orderId);
        var rentedOrderItems = orderItemRepository.findByOrderId(orderId)
                .stream()
                .peek(OrderItem::startOrderItemRental)
                .toList();
        log.info("Renting order items={}", rentedOrderItems);
        var inventoryItemIds = rentedOrderItems.stream()
                .map(OrderItem::getInventoryItemId)
                .toList();
        inventoryIntegrationService.rent(inventoryItemIds);
        return orderItemRepository.saveAll(rentedOrderItems);
    }

    @Override
    public OrderItem returnOrderItem(UUID orderItemId) {
        log.info("Return order item id={}", orderItemId);
        var orderItemToReturn = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> OrderItemNotFoundException.notFoundById(orderItemId));

        orderItemToReturn.endOrderItemRental();

        inventoryIntegrationService.returnItem(orderItemToReturn.getInventoryItemId());
        return orderItemRepository.save(orderItemToReturn);
    }

    @Override
    public Collection<OrderItem> cancelOrderItemsByOrderId(UUID orderId) {
        log.info("Cancel order items by orderId {}", orderId);
        var cancelledOrderItems = orderItemRepository.findByOrderId(orderId)
                .stream()
                .peek(OrderItem::cancelOrderItemRental)
                .toList();

        var inventoryItemIds = cancelledOrderItems.stream()
                .map(OrderItem::getInventoryItemId)
                .toList();
        log.info("Canceling order items {}", cancelledOrderItems);
        inventoryIntegrationService.cancelReserveByIds(inventoryItemIds);
        return orderItemRepository.saveAll(cancelledOrderItems);
    }

    @Override
    public Collection<OrderItem> findOrderItemsByOrderId(UUID orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    @Override
    public void cancelOrderItemById(UUID orderItemId) {
        log.info("Deleting order item {}", orderItemId);
        var orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> OrderItemNotFoundException.notFoundById(orderItemId));
        inventoryIntegrationService.cancelReserveByIds(Collections.singleton(orderItem.getInventoryItemId()));
        log.info("Cancelled order item {}", orderItemId);
        orderItem.cancelOrderItemRental();
        orderItemRepository.save(orderItem);
    }

    @Override
    public Collection<OrderItem> findActiveOrderItemsByRenterId(UUID renterId) {
        return orderItemRepository.findAllByRenterIdAndRentalStatus(renterId, RentalStatus.ACTIVE);
    }

    @Override
    public Collection<OrderItem> findCompleted(UUID renterId) {
        return orderItemRepository.findAllByRenterIdAndRentalStatus(renterId, RentalStatus.COMPLETED);

    }

    @Override
    public Collection<OrderItem> findPending(UUID renterId) {
        return orderItemRepository.findAllByRenterIdAndRentalStatus(renterId, RentalStatus.PENDING);
    }

    @Override
    public Collection<OrderItem> findPendingByOrderId(UUID orderID) {
        return orderItemRepository.findAllByOrderId(orderID)
                .stream()
                .filter(orderItem -> orderItem.getRental().getStatus().equals(RentalStatus.PENDING))
                .toList();
    }
}
