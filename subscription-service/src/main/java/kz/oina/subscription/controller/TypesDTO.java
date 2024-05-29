package kz.oina.subscription.controller;

import kz.oina.subscription.entity.SubscriptionTypeName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TypesDTO {
    private UUID id;
    private SubscriptionTypeName name;
    private BigDecimal price;
    private Integer tokensProvided;
}
