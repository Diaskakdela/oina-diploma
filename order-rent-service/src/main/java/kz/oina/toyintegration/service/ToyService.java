package kz.oina.toyintegration.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public interface ToyService {

    BigDecimal calculatePrice(Map<UUID, Integer> toyIds);
}
