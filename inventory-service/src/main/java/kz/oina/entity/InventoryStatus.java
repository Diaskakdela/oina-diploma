package kz.oina.entity;

import java.util.Set;

public enum InventoryStatus {
    AVAILABLE,     // Доступно для аренды
    RENTED,        // В аренде
    RESERVED,      // Забронировано
    BROKEN;         // Сломано

    public static final Set<InventoryStatus> UNAVAILABLE_STATUSES = Set.of(RESERVED, RENTED);
}
