package kz.oina.oinatokens.web.model;

import java.util.UUID;

public record CreditTokensResponse(UUID transactionId, String type) {
}
