package kz.oina.integrations.toy.service;

import kz.oina.integrations.toy.client.ToyClient;
import kz.oina.integrations.toy.model.ToyPricesRequest;
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
        return toyClient.requestToyPrices(new ToyPricesRequest(toyIds));
    }
}
