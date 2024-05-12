package kz.oina.inventoryintegration.model.integration.response;

import java.util.Collection;

public record RentInventoryIntegrationResponse(boolean success,
                                               String message,
                                               Collection<Data> data) {
}
