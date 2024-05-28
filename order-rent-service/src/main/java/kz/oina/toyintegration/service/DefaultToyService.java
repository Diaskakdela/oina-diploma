package kz.oina.toyintegration.service;

import kz.oina.toyintegration.client.ToyClient;
import kz.oina.toyintegration.model.ToyPricesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultToyService implements ToyService {
    private final ToyClient toyClient;

    @Override
    public BigDecimal calculatePrice(Map<UUID, Integer> toyIds) {
        return toyClient.requestToyPrices(new ToyPricesRequest(toyIds)).price();
    }
}
