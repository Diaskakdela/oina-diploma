package kz.oina.subscription.service;

import kz.oina.subscription.entity.Subscription;
import kz.oina.subscription.model.SubscriptionCreationParams;

import java.util.Optional;
import java.util.UUID;

public interface SubscriptionService {
    Optional<Subscription> findByUserId(UUID userId);

    Subscription createSubscription(SubscriptionCreationParams subscription);



}
