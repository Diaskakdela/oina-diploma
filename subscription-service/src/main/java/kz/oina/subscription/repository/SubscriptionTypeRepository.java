package kz.oina.subscription.repository;

import kz.oina.subscription.entity.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType, UUID> {
}
