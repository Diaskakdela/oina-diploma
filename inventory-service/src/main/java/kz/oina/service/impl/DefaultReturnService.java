package kz.oina.service.impl;

import kz.oina.entity.InventoryItem;
import kz.oina.exceptions.item.ItemNotFoundException;
import kz.oina.model.ReturnParams;
import kz.oina.repository.InventoryItemRepository;
import kz.oina.service.ReturnService;
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
public class DefaultReturnService implements ReturnService {

    private final InventoryItemRepository inventoryItemRepository;

    @Override
    @Transactional
    public InventoryItem returnItem(ReturnParams returnParams) {
        InventoryItem inventoryItem = inventoryItemRepository.findById(returnParams.inventoryItemId())
                .orElseThrow(() -> new ItemNotFoundException("Item not found. id=" + returnParams.inventoryItemId()));

        inventoryItem.returnItem();

        return inventoryItemRepository.save(inventoryItem);
    }

    @Override
    @Transactional
    public Collection<InventoryItem> returnItems(Collection<ReturnParams> returnParams) {
        List<UUID> itemIds = returnParams.stream()
                .map(ReturnParams::inventoryItemId)
                .toList();
        List<InventoryItem> returnedItems = inventoryItemRepository.findByIdIn(itemIds)
                .stream()
                .peek(InventoryItem::returnItem)
                .toList();

        return inventoryItemRepository.saveAll(returnedItems);
    }
}
