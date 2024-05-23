package kz.oina.payment.model;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentCreationParams(UUID userId, BigDecimal amount) {
}
