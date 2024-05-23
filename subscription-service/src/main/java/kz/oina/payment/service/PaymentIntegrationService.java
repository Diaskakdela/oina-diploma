package kz.oina.payment.service;

import kz.oina.payment.model.PaymentDetails;
import kz.oina.payment.model.PaymentIntegrationParams;

public interface PaymentIntegrationService {
    PaymentDetails makePayment(PaymentIntegrationParams params);
}
