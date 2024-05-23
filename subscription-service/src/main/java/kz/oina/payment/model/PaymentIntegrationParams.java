package kz.oina.payment.model;

import java.math.BigDecimal;

public record PaymentIntegrationParams(String account,
                                       String fullName,
                                       String expirationDate,
                                       String cvvCode,
                                       BigDecimal amount) {
}
