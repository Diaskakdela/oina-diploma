package kz.oina.inventoryintegration.service;

import kz.oina.inventoryintegration.exception.InventoryException;
import kz.oina.inventoryintegration.model.Inventory;
import kz.oina.inventoryintegration.model.ReserveInventoryParams;

import java.util.Collection;
import java.util.UUID;

public interface InventoryIntegrationService {
    Collection<Inventory> reserveInventory(ReserveInventoryParams reserveInventoryParams) throws InventoryException;

    void cancelReserve(Collection<Inventory> inventoryItems) throws InventoryException;

    void cancelReserveByIds(Collection<UUID> inventoryItemIds) throws InventoryException;

    Collection<Inventory> rent(Collection<UUID> inventoryItemIds) throws InventoryException;

    void returnItem(UUID inventoryItemId) throws InventoryException;
}
