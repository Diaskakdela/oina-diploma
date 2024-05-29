package kz.oina.integrations.inventory.mapper;

import kz.oina.integrations.inventory.model.Inventory;
import kz.oina.integrations.inventory.model.ReserveInventoryParams;
import kz.oina.integrations.inventory.model.integration.request.ReserveInventoryIntegrationRequest;
import kz.oina.integrations.inventory.model.integration.response.Data;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryItemMapper {
    ReserveInventoryIntegrationRequest toReserveInventoryIntegrationRequest(ReserveInventoryParams params);

    Inventory toInventory(Data data);

}
