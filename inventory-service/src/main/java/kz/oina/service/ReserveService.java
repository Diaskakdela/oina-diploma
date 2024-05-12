package kz.oina.service;

import kz.oina.entity.InventoryItem;
import kz.oina.model.CancelReserveParams;
import kz.oina.model.ReserveParams;

import java.util.Collection;

public interface ReserveService {

    Collection<InventoryItem> reserve(ReserveParams reserveParams);

    InventoryItem cancelReserve(CancelReserveParams cancelReserveParams);

    Collection<InventoryItem> cancelReserve(Collection<CancelReserveParams> cancelReserveParams);
}
