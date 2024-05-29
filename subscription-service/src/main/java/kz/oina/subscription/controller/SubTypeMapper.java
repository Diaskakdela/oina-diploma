package kz.oina.subscription.controller;

import kz.oina.subscription.entity.SubscriptionType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubTypeMapper {
    TypesDTO toDto(SubscriptionType subscriptionType);
}
