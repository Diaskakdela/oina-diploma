package kz.oina.integrations.inventory.service;

import kz.oina.integrations.inventory.exception.InventoryException;
import kz.oina.integrations.inventory.model.Inventory;
import kz.oina.integrations.inventory.model.ReserveInventoryParams;

import java.util.Collection;
import java.util.UUID;

public interface InventoryIntegrationService {
    Collection<Inventory> reserveInventory(ReserveInventoryParams reserveInventoryParams) throws InventoryException;

    void cancelReserve(Collection<Inventory> inventoryItems) throws InventoryException;

    void cancelReserveByIds(Collection<UUID> inventoryItemIds) throws InventoryException;

    Collection<Inventory> rent(Collection<UUID> inventoryItemIds) throws InventoryException;

    void returnItem(UUID inventoryItemId) throws InventoryException;
}
