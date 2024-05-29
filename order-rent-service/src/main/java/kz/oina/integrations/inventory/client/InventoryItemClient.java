package kz.oina.integrations.inventory.client;

import kz.oina.integrations.inventory.model.integration.request.CancelReserveIntegrationRequest;
import kz.oina.integrations.inventory.model.integration.request.RentInventoryIntegrationRequest;
import kz.oina.integrations.inventory.model.integration.request.ReserveInventoryIntegrationRequest;
import kz.oina.integrations.inventory.model.integration.request.ReturnInventoryIntegrationRequest;
import kz.oina.integrations.inventory.model.integration.response.CancelInventoryIntegrationResponse;
import kz.oina.integrations.inventory.model.integration.response.RentInventoryIntegrationResponse;
import kz.oina.integrations.inventory.model.integration.response.ReserveInventoryIntegrationResponse;
import kz.oina.integrations.inventory.model.integration.response.ReturnInventoryIntegrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class InventoryItemClient {
    private final RestClient inventoryItemRestClient;

    public ReserveInventoryIntegrationResponse reserveInventory(ReserveInventoryIntegrationRequest reserveInventoryIntegrationRequest) {
        return inventoryItemRestClient.post()
                .uri("/inventory/service/reserve")
                .body(reserveInventoryIntegrationRequest)
                .retrieve()
                .body(ReserveInventoryIntegrationResponse.class);
    }

    public CancelInventoryIntegrationResponse cancelAllReserve(Collection<CancelReserveIntegrationRequest> cancelReserveIntegrationRequest) {
        return inventoryItemRestClient.post()
                .uri("/inventory/service/cancel-reserve-all")
                .body(cancelReserveIntegrationRequest)
                .retrieve()
                .body(CancelInventoryIntegrationResponse.class);
    }

    public RentInventoryIntegrationResponse rentAllInventories(Collection<RentInventoryIntegrationRequest> rentInventoryIntegrationRequest) {
        return inventoryItemRestClient.post()
                .uri("/inventory/service/rent-all")
                .body(rentInventoryIntegrationRequest)
                .retrieve()
                .body(RentInventoryIntegrationResponse.class);
    }

    public ReturnInventoryIntegrationResponse returnAllInventory(Collection<ReturnInventoryIntegrationRequest> returnInventoryIntegrationRequest) {
        return inventoryItemRestClient.post()
                .uri("/inventory/service/return-all")
                .body(returnInventoryIntegrationRequest)
                .retrieve()
                .body(ReturnInventoryIntegrationResponse.class);
    }
}
