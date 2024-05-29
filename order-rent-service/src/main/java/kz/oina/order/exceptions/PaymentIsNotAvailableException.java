package kz.oina.order.exceptions;

import kz.oina.integrations.subscription.model.PaymentAvailabilityStatus;
import lombok.Getter;

@Getter
public class PaymentIsNotAvailableException extends RuntimeException {
    private final PaymentAvailabilityStatus paymentAvailabilityStatus;

    public PaymentIsNotAvailableException(String message, PaymentAvailabilityStatus status) {
        super(message);
        this.paymentAvailabilityStatus = status;
    }
}
