package kz.oina.subscription.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Entity
public class SubscriptionType {
    @Id
    private UUID id;
    @Enumerated(EnumType.STRING)
    private SubscriptionTypeName name;
    private BigDecimal price;
    private Integer durationInDays;
    private Integer tokensProvided;
}
