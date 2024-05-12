package kz.oina.mapper;

import kz.oina.model.CancelReserveParams;
import kz.oina.model.ReserveParams;
import kz.oina.model.request.CancelReserveRequest;
import kz.oina.model.request.ReserveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReserveMapper extends InventoryItemMapper {

    ReserveParams toReserveParams(ReserveRequest reserveRequest);

    CancelReserveParams toCancelReserveParams(CancelReserveRequest cancelReserveRequest);
}
