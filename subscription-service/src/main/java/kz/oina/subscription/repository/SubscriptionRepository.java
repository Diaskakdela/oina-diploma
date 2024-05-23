package kz.oina.subscription.repository;

import kz.oina.subscription.entity.Subscription;
import kz.oina.subscription.entity.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    Optional<Subscription> findByUserId(UUID uuid);

    Optional<Subscription> findByUserIdAndStatus(UUID userId, SubscriptionStatus currentDate);
}
