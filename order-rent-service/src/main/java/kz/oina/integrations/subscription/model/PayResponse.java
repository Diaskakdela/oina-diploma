package kz.oina.integrations.subscription.model;

import java.util.UUID;

public record PayResponse(boolean success, Data data) {

    public record Data(UUID transactionId, String type) {

    }
}
