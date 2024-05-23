package kz.oina.subscription.service;

import kz.oina.subscription.entity.SubscriptionType;
import kz.oina.subscription.repository.SubscriptionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultSubscriptionTypeService implements SubscriptionTypeService {
    private final SubscriptionTypeRepository subscriptionTypeRepository;

    public Collection<SubscriptionType> findAllSubscriptionTypes() {
        return subscriptionTypeRepository.findAll();
    }

    public Optional<SubscriptionType> findSubscriptionTypeById(UUID id) {
        return subscriptionTypeRepository.findById(id);
    }
}
