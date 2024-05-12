package kz.oina.mapper;

import kz.oina.entity.InventoryItem;
import kz.oina.model.response.SimpleInventoryItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryItemMapper {
    SimpleInventoryItemResponse toSimpleInventoryItemResponse(InventoryItem inventoryItem);
}
