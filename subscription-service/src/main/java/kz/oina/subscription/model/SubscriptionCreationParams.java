package kz.oina.subscription.model;

import java.util.UUID;

public record SubscriptionCreationParams(UUID renterId, UUID subscriptionTypeId) {
}
