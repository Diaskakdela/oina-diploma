package kz.oina.integrations.toy.model;

import java.util.Map;
import java.util.UUID;

public record ToyPricesRequest(Map<UUID, Integer> ToyIds) {
}
