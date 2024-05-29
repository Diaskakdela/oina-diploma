package kz.oina.oinatokens.web.model;

import java.util.UUID;

public record DebitTokensRequest(UUID renterId,
                                 Integer amount) {
}
