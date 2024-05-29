package kz.oina.subscription.controller;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class SubscriptionDTO {
    private UUID subscriptionId;
    private String status;
}
