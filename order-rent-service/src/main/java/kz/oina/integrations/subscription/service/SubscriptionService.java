package kz.oina.integrations.subscription.service;

import kz.oina.integrations.subscription.model.PaymentAvailabilityStatus;

import java.math.BigDecimal;
import java.util.UUID;

public interface SubscriptionService {
    PaymentAvailabilityStatus findPaymentAvailabilityStatus(UUID renterId, BigDecimal amount);
}
