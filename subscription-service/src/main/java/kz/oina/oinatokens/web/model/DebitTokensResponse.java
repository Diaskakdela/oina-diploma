package kz.oina.oinatokens.web.model;

import java.util.UUID;

public record DebitTokensResponse(UUID transactionId, String type) {
}
