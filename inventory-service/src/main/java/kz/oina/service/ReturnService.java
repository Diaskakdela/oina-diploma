package kz.oina.service;

import kz.oina.entity.InventoryItem;
import kz.oina.model.ReturnParams;

import java.util.Collection;

public interface ReturnService {

    InventoryItem returnItem(ReturnParams returnParams);

    Collection<InventoryItem> returnItems(Collection<ReturnParams> returnParams);
}
