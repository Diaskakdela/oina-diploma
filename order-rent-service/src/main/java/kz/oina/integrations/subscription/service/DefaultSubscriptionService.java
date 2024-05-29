package kz.oina.integrations.subscription.service;

import kz.oina.integrations.subscription.client.SubscriptionClient;
import kz.oina.integrations.subscription.model.PayAvailableRequest;
import kz.oina.integrations.subscription.model.PaymentAvailabilityStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultSubscriptionService implements SubscriptionService {
    private final SubscriptionClient subscriptionClient;

    public PaymentAvailabilityStatus findPaymentAvailabilityStatus(UUID renterId, BigDecimal amount) {
        try {
            var response = subscriptionClient.receivePayAvailability(new PayAvailableRequest(renterId, amount));
            return PaymentAvailabilityStatus.valueOf(response.status());
        } catch (Exception e) {
            return PaymentAvailabilityStatus.ERROR;
        }
    }
}
