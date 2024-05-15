package kz.oina.mapper;

import kz.oina.model.InventoryToyDetails;
import kz.oina.model.response.ToyAvailabilityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryMapper {
    ToyAvailabilityResponse toyAvailabilityResponse(InventoryToyDetails inventoryToyDetails);
}
