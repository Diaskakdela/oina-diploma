package kz.oina.payment.service;

import kz.oina.payment.entity.PaymentMethod;
import kz.oina.payment.model.PaymentCreationParams;

public interface PaymentService {
    PaymentMethod makePayment(PaymentCreationParams params);
}
