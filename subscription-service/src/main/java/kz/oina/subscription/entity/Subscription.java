package kz.oina.subscription.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Entity
public class Subscription {
    @Id
    private UUID id;
    private UUID userId;
    private UUID subscriptionTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    public Subscription(UUID userId, UUID subscriptionTypeId) {
        id = UUID.randomUUID();
        this.userId = userId;
        this.subscriptionTypeId = subscriptionTypeId;
        startDate = LocalDate.now();
        endDate = LocalDate.now().plusMonths(1);
        status = SubscriptionStatus.ACTIVE;
    }

    public Subscription() {

    }
}
