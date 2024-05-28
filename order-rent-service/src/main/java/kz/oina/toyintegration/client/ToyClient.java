package kz.oina.toyintegration.client;

import kz.oina.toyintegration.model.ToyPricesRequest;
import kz.oina.toyintegration.model.ToyPricesResponse;
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
