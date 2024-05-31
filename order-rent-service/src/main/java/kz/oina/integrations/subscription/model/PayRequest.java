package kz.oina.integrations.subscription.model;

import java.math.BigDecimal;
import java.util.UUID;

public record PayRequest(UUID renterId, BigDecimal amount) {
}
