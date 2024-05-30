package kz.oina.integrations.toy.client;

import kz.oina.integrations.toy.model.ToyPricesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ToyClient {

    private final RestClient toyRestClient;

    public BigDecimal requestToyPrices(ToyPricesRequest toyPricesRequest) {
        return toyRestClient.post()
                .uri("/toys/calculate-price")
                .body(toyPricesRequest)
                .retrieve()
                .body(BigDecimal.class);
    }
}
