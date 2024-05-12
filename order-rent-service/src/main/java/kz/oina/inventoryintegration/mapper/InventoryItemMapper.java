package kz.oina.inventoryintegration.mapper;

import kz.oina.inventoryintegration.model.Inventory;
import kz.oina.inventoryintegration.model.ReserveInventoryParams;
import kz.oina.inventoryintegration.model.integration.request.ReserveInventoryIntegrationRequest;
import kz.oina.inventoryintegration.model.integration.response.Data;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryItemMapper {
    ReserveInventoryIntegrationRequest toReserveInventoryIntegrationRequest(ReserveInventoryParams params);

    Inventory toInventory(Data data);

}
