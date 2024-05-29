package kz.oina.integrations.inventory.model.integration.response;

import java.util.Collection;

public record ReturnInventoryIntegrationResponse(boolean success,
                                                 String message,
                                                 Collection<Data> data) {
}
