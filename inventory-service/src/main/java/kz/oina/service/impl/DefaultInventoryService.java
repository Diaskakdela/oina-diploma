package kz.oina.service.impl;

import kz.oina.entity.InventoryStatus;
import kz.oina.model.InventoryToyDetails;
import kz.oina.repository.InventoryItemRepository;
import kz.oina.repository.ToyIdCountProjection;
import kz.oina.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Log4j2
public class DefaultInventoryService implements InventoryService {

    private final InventoryItemRepository inventoryItemRepository;

    @Override
    public Collection<InventoryToyDetails> findAvailableToReserveToys() {
        return inventoryItemRepository.countAvailableToysGroupedByToyId(InventoryStatus.AVAILABLE)
                .stream()
                .map(this::mapToInventoryToyDetails)
                .toList();
    }

    @Override
    public InventoryToyDetails findToyAvailability(UUID toyId) {
        if (!inventoryItemRepository.existsByToyId(toyId)) {
            return new InventoryToyDetails(toyId, 0);
        }
        int count = inventoryItemRepository.countByToyIdAndStatus(toyId, InventoryStatus.AVAILABLE);
        return new InventoryToyDetails(toyId, count);
    }

    private InventoryToyDetails mapToInventoryToyDetails(ToyIdCountProjection toyIdCountProjection) {
        return new InventoryToyDetails(toyIdCountProjection.getToyId(), toyIdCountProjection.getCount());
    }
}
