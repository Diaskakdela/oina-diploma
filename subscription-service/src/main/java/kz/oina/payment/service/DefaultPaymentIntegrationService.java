package kz.oina.payment.service;

import kz.oina.payment.model.PaymentDetails;
import kz.oina.payment.model.PaymentIntegrationParams;
import kz.oina.payment.model.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultPaymentIntegrationService implements PaymentIntegrationService {

    @Override
    public PaymentDetails makePayment(PaymentIntegrationParams params) {
        return new PaymentDetails(PaymentStatus.SUCCESS, "Payment success");
    }
}
