package kz.oina.payment.web;

import java.util.UUID;

public record UserAccountCreationRequest(UUID renterId,
                                         String account,
                                         String fullName,
                                         String expirationDate,
                                         String cvvCode) {
}
