package kz.oina.core.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayAvailabilityStatus {
    AVAILABLE("AVAILABLE"),
    NOT_ENOUGH_TOKENS("NOT_ENOUGH_TOKENS"),
    NO_SUBSCRIPTION("NO_SUBSCRIPTION");
    private final String name;
}
