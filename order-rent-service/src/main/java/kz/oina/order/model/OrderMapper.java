package kz.oina.order.model;

import kz.oina.order.web.request.OrderCreationRequest;
import kz.oina.order.web.response.OrderWithOrderItemsDTO;
import kz.oina.orderitem.entity.OrderItem;
import kz.oina.orderitem.web.response.OrderItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Collection;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    OrderCreationParam toOrderCreationParam(OrderCreationRequest orderCreationRequest);

    OrderWithOrderItemsDTO toDto(OrderWithOrderItems orderWithOrderItems);


    @Mapping(source = "order.id", target = "order.id")
    @Mapping(source = "order.renterId", target = "order.renterId")
    @Mapping(expression = "java(order.getStatus().name())", target = "order.status")
    OrderWithOrderItemsDTO mapOrderWithItems(OrderWithOrderItems orderWithItems);

    Collection<OrderItemDTO> mapOrderItems(Collection<OrderItem> orderItems);

    @Mapping(expression = "java(orderItem.getRentalStatus())", target = "status")
    OrderItemDTO mapOrderItem(OrderItem orderItem);
}
