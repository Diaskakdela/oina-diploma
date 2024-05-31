package kz.oina.integrations.subscription.service;

import kz.oina.integrations.subscription.client.SubscriptionClient;
import kz.oina.integrations.subscription.model.PayAvailableRequest;
import kz.oina.integrations.subscription.model.PayRequest;
import kz.oina.integrations.subscription.model.PaymentAvailabilityStatus;
import kz.oina.order.exceptions.PaymentException;
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
            return PaymentAvailabilityStatus.valueOf(response.data().status());
        } catch (Exception e) {
            return PaymentAvailabilityStatus.ERROR;
        }
    }

    @Override
    public void pay(UUID renterId, BigDecimal amount) {
        try {
            var response = subscriptionClient.pay(new PayRequest(renterId, amount));
            if (!response.success()) {
                throw new PaymentException();
            }
        } catch (Exception e) {
            throw new PaymentException();
        }
    }
}
