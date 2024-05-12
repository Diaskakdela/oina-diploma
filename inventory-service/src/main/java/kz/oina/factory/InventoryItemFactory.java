package kz.oina.factory;

import kz.oina.entity.InventoryItem;
import kz.oina.model.CreationParams;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
public class InventoryItemFactory {

    public Collection<InventoryItem> createInventoryItems(CreationParams creationParams) {
        int count = creationParams.count();
        UUID toyId = creationParams.toyId();
        String location = creationParams.location();

        return IntStream.range(0, count)
                .mapToObj(i -> new InventoryItem(toyId, location))
                .toList();
    }
}
