package kz.oina.mapper;

import kz.oina.model.CreationParams;
import kz.oina.model.request.AddRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddMapper extends InventoryItemMapper {

    CreationParams toCreationParams(AddRequest inventoryItem);
}
