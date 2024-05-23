package kz.oina.subscription.factory;

import kz.oina.subscription.entity.Subscription;
import kz.oina.subscription.model.SubscriptionCreationParams;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionFactory {
    public Subscription createSubscription(SubscriptionCreationParams creationParams) {
        return new Subscription(creationParams.userId(), creationParams.subscriptionTypeId());
    }
}
