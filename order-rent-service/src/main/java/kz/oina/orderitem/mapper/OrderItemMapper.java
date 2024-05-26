package kz.oina.orderitem.mapper;

import kz.oina.orderitem.entity.OrderItem;
import kz.oina.orderitem.model.OrderItemCreationParams;
import kz.oina.orderitem.web.request.OrderItemAddRequest;
import kz.oina.orderitem.web.response.OrderItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(expression = "java(orderItem.getRentalStatus())", target = "status")
    OrderItemDTO toDto(OrderItem orderItem);

    OrderItemCreationParams toCreateParams(OrderItemAddRequest request);
}
