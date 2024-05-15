package kz.oina.service;

import kz.oina.model.InventoryToyDetails;

import java.util.Collection;
import java.util.UUID;

public interface InventoryService {
    Collection<InventoryToyDetails> findAvailableToReserveToys();

    InventoryToyDetails findToyAvailability(UUID toyId);
}
