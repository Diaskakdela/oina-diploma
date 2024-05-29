package kz.oina.integrations.inventory.service;

import kz.oina.integrations.inventory.exception.InventoryException;
import kz.oina.integrations.inventory.model.Inventory;
import kz.oina.integrations.inventory.model.ReserveInventoryParams;
import kz.oina.integrations.inventory.model.integration.request.CancelReserveIntegrationRequest;
import kz.oina.integrations.inventory.model.integration.request.RentInventoryIntegrationRequest;
import kz.oina.integrations.inventory.model.integration.request.ReturnInventoryIntegrationRequest;
import kz.oina.integrations.inventory.client.InventoryItemClient;
import kz.oina.integrations.inventory.mapper.InventoryItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultInventoryIntegrationService implements InventoryIntegrationService {

    private final InventoryItemClient inventoryItemClient;
    private final InventoryItemMapper inventoryItemMapper;

    @Override
    public Collection<Inventory> reserveInventory(ReserveInventoryParams reserveInventoryParams) throws InventoryException {
        var integrationRequest = inventoryItemMapper.toReserveInventoryIntegrationRequest(reserveInventoryParams);
        var integrationResponse = inventoryItemClient.reserveInventory(integrationRequest);

        if (!integrationResponse.success()) {
            throw new InventoryException("Error while reserving toy with id=" + reserveInventoryParams.toyId() + ". Response from inventory service is not success. Response: " + integrationResponse.message());
        }

        return integrationResponse.data()
                .stream()
                .map(inventoryItemMapper::toInventory)
                .toList();
    }

    @Async
    @Override
    public void cancelReserve(Collection<Inventory> inventoryItems) throws InventoryException {
        var integrationRequest = inventoryItems.stream()
                .map(Inventory::id)
                .map(CancelReserveIntegrationRequest::new)
                .toList();
        var integrationResponse = inventoryItemClient.cancelAllReserve(integrationRequest);
        if (!integrationResponse.success()) {
            throw new InventoryException("Error while cancelling reserve. Response from inventory service is not success. Response: " + integrationResponse.message());
        }
    }

    @Override
    public void cancelReserveByIds(Collection<UUID> inventoryItemIds) throws InventoryException {
        var integrationRequest = inventoryItemIds.stream()
                .map(CancelReserveIntegrationRequest::new)
                .toList();
        var integrationResponse = inventoryItemClient.cancelAllReserve(integrationRequest);
        if (!integrationResponse.success()) {
            throw new InventoryException("Error while cancelling reserve. Response from inventory service is not success. Response: " + integrationResponse.message());
        }
    }

    @Override
    public Collection<Inventory> rent(Collection<UUID> inventoryItemIds) throws InventoryException {
        var integrationRequests = inventoryItemIds.stream()
                .map(RentInventoryIntegrationRequest::new)
                .toList();
        var integrationResponse = inventoryItemClient.rentAllInventories(integrationRequests);
        if (!integrationResponse.success()) {
            throw new InventoryException("Error while renting. Response from inventory service is not success. Response: " + integrationResponse.message());
        }
        return integrationResponse.data()
                .stream()
                .map(inventoryItemMapper::toInventory)
                .toList();
    }

    @Override
    public void returnItem(UUID inventoryItemId) throws InventoryException {
        var integrationRequests = Collections.singleton(new ReturnInventoryIntegrationRequest(inventoryItemId));
        var integrationResponse = inventoryItemClient.returnAllInventory(integrationRequests);
        if (!integrationResponse.success()) {
            throw new InventoryException("Error while returning item. Response from inventory service is not success. Response: " + integrationResponse.message());
        }
    }

}