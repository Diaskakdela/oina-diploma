package kz.oina.subscription.service;

import kz.oina.subscription.entity.SubscriptionType;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionTypeService {

    Collection<SubscriptionType> findAllSubscriptionTypes();

    Optional<SubscriptionType> findSubscriptionTypeById(UUID id);
}
