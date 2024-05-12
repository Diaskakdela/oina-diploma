package kz.oina.service;

import kz.oina.entity.InventoryItem;
import kz.oina.model.CreationParams;

import java.util.Collection;

public interface AddService {

    Collection<InventoryItem> addItem(CreationParams creationParams);

}
