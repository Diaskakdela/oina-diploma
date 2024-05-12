package kz.oina.mapper;

import kz.oina.model.RentParams;
import kz.oina.model.request.RentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RentMapper extends InventoryItemMapper {

    RentParams toRentParams(RentRequest rentRequest);
}
