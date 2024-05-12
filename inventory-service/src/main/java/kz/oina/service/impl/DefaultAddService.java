package kz.oina.service.impl;

import kz.oina.entity.InventoryItem;
import kz.oina.factory.InventoryItemFactory;
import kz.oina.model.CreationParams;
import kz.oina.repository.InventoryItemRepository;
import kz.oina.service.AddService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Log4j2
public class DefaultAddService implements AddService {
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemFactory inventoryItemFactory;

    @Override
    public Collection<InventoryItem> addItem(CreationParams creationParams) {
        Collection<InventoryItem> inventoryItems = inventoryItemFactory.createInventoryItems(creationParams);
        return inventoryItemRepository.saveAll(inventoryItems);
    }
}
