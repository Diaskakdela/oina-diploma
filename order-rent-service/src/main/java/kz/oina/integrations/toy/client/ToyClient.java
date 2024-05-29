package kz.oina.integrations.toy.client;

import kz.oina.integrations.toy.model.ToyPricesRequest;
import kz.oina.integrations.toy.model.ToyPricesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class ToyClient {

    private final RestClient toyRestClient;

    public ToyPricesResponse requestToyPrices(ToyPricesRequest toyPricesRequest) {
        return toyRestClient.post()
                .uri("/toys/calculate-price")
                .body(toyPricesRequest)
                .retrieve()
                .body(ToyPricesResponse.class);
    }
}
