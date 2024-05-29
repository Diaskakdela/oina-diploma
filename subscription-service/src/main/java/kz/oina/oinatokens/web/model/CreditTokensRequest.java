package kz.oina.oinatokens.web.model;

import java.util.UUID;

public record CreditTokensRequest(UUID renterId,
                                  Integer amount) {
}
