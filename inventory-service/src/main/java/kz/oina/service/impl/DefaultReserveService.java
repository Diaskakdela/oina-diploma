package kz.oina.service.impl;

import kz.oina.entity.InventoryItem;
import kz.oina.entity.InventoryStatus;
import kz.oina.exceptions.item.InsufficientInventoryException;
import kz.oina.exceptions.item.ItemNotFoundException;
import kz.oina.model.CancelReserveParams;
import kz.oina.model.ReserveParams;
import kz.oina.repository.InventoryItemRepository;
import kz.oina.service.ReserveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Log4j2
public class DefaultReserveService implements ReserveService {

    private final InventoryItemRepository inventoryItemRepository;

    @Override
    @Transactional
    public Collection<InventoryItem> reserve(ReserveParams reserveParams) {
        var toyId = reserveParams.toyId();
        var count = reserveParams.count();

        List<InventoryItem> reservedItems = findInventoryItemsAndValidate(toyId, count)
                .stream()
                .peek(InventoryItem::reserveItem)
                .toList();

        return inventoryItemRepository.saveAll(reservedItems);
    }

    @Override
    @Transactional
    public InventoryItem cancelReserve(CancelReserveParams cancelReserveParams) {
        var inventoryItemId = cancelReserveParams.inventoryItemId();
        var inventoryItem = inventoryItemRepository.findById(inventoryItemId)
                .orElseThrow(() -> ItemNotFoundException.notFoundByInventoryItemId(inventoryItemId));

        inventoryItem.cancelReservation();

        return inventoryItemRepository.save(inventoryItem);
    }

    @Override
    @Transactional
    public Collection<InventoryItem> cancelReserve(Collection<CancelReserveParams> cancelReserveParams) {
        return cancelReserveParams.stream()
                .map(this::cancelReserve)
                .toList();
    }

    private Collection<InventoryItem> findInventoryItemsAndValidate(UUID toyId, int count) {
        var inventoryItems = inventoryItemRepository.findByToyIdAndStatus(toyId, InventoryStatus.AVAILABLE.name(), count);

        if (inventoryItems.isEmpty()) {
            throw ItemNotFoundException.notFoundByToyId(toyId);
        }

        if (inventoryItems.size() != count) {
            throw new InsufficientInventoryException("Inventory items count mismatch for toyId: " + toyId);
        }

        return inventoryItems;
    }
}
