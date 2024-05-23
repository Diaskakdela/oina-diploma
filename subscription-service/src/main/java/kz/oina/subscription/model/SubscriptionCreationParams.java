package kz.oina.subscription.model;

import java.util.UUID;

public record SubscriptionCreationParams(UUID userId, UUID subscriptionTypeId) {
}
