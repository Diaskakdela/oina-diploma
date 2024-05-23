package kz.oina.payment.model;

import java.util.UUID;

public record UserAccountCreationParams(UUID userId,
                                        String account,
                                        String fullName,
                                        String expirationDate,
                                        String cvvCode) {
}
