package kz.oina.service;

import kz.oina.entity.InventoryItem;
import kz.oina.model.RentParams;

import java.util.Collection;

public interface RentService {
    InventoryItem rent(RentParams rentParams);

    Collection<InventoryItem> rent(Collection<RentParams> rentParamsList);
}
