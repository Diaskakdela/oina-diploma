package kz.oina.orderitem.model;

import kz.oina.inventoryintegration.model.Inventory;

import java.util.Collection;
import java.util.UUID;

public record OrderItemDetails(Collection<Inventory> inventoryItems,
                               UUID orderId,
                               UUID renterId) {
}
