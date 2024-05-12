package kz.oina.inventoryintegration.client;

import kz.oina.inventoryintegration.model.integration.request.CancelReserveIntegrationRequest;
import kz.oina.inventoryintegration.model.integration.request.RentInventoryIntegrationRequest;
import kz.oina.inventoryintegration.model.integration.request.ReserveInventoryIntegrationRequest;
import kz.oina.inventoryintegration.model.integration.request.ReturnInventoryIntegrationRequest;
import kz.oina.inventoryintegration.model.integration.response.CancelInventoryIntegrationResponse;
import kz.oina.inventoryintegration.model.integration.response.RentInventoryIntegrationResponse;
import kz.oina.inventoryintegration.model.integration.response.ReserveInventoryIntegrationResponse;
import kz.oina.inventoryintegration.model.integration.response.ReturnInventoryIntegrationResponse;
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
                .uri("/inventory/reserve")
                .body(reserveInventoryIntegrationRequest)
                .retrieve()
                .body(ReserveInventoryIntegrationResponse.class);
    }

    public CancelInventoryIntegrationResponse cancelAllReserve(Collection<CancelReserveIntegrationRequest> cancelReserveIntegrationRequest) {
        return inventoryItemRestClient.post()
                .uri("/inventory/cancel-reserve-all")
                .body(cancelReserveIntegrationRequest)
                .retrieve()
                .body(CancelInventoryIntegrationResponse.class);
    }

    public RentInventoryIntegrationResponse rentAllInventories(Collection<RentInventoryIntegrationRequest> rentInventoryIntegrationRequest) {
        return inventoryItemRestClient.post()
                .uri("/inventory/rent-all")
                .body(rentInventoryIntegrationRequest)
                .retrieve()
                .body(RentInventoryIntegrationResponse.class);
    }

    public ReturnInventoryIntegrationResponse returnAllInventory(Collection<ReturnInventoryIntegrationRequest> returnInventoryIntegrationRequest) {
        return inventoryItemRestClient.post()
                .uri("/inventory/return-all")
                .body(returnInventoryIntegrationRequest)
                .retrieve()
                .body(ReturnInventoryIntegrationResponse.class);
    }
}
