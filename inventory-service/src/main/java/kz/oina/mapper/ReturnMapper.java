package kz.oina.mapper;

import kz.oina.model.CreationParams;
import kz.oina.model.ReturnParams;
import kz.oina.model.request.AddRequest;
import kz.oina.model.request.ReturnRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReturnMapper extends InventoryItemMapper {

    ReturnParams toReturnParams(ReturnRequest returnRequest);
}
