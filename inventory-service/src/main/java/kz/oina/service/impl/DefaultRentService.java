package kz.oina.service.impl;

import kz.oina.entity.InventoryItem;
import kz.oina.exceptions.item.ItemNotFoundException;
import kz.oina.model.RentParams;
import kz.oina.repository.InventoryItemRepository;
import kz.oina.service.RentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Log4j2
public class DefaultRentService implements RentService {
    private final InventoryItemRepository inventoryItemRepository;

    @Override
    @Transactional
    public InventoryItem rent(RentParams rentParams) {
        UUID inventoryItemId = rentParams.inventoryItemId();
        var item = inventoryItemRepository.findById(inventoryItemId)
                .orElseThrow(() -> ItemNotFoundException.notFoundByInventoryItemId(inventoryItemId));
        item.rentItem();
        return inventoryItemRepository.save(item);
    }

    @Override
    @Transactional
    public Collection<InventoryItem> rent(Collection<RentParams> rentParamsList) {
        return rentParamsList.stream().map(this::rent).toList();
    }
}
