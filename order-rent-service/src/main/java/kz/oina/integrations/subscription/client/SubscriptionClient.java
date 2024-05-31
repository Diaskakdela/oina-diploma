package kz.oina.integrations.subscription.client;

import kz.oina.integrations.subscription.model.PayAvailableRequest;
import kz.oina.integrations.subscription.model.PayAvailableResponse;
import kz.oina.integrations.subscription.model.PayRequest;
import kz.oina.integrations.subscription.model.PayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class SubscriptionClient {
    private final RestClient subscriptionRestClient;

    public PayAvailableResponse receivePayAvailability(PayAvailableRequest payAvailableRequest) {
        return subscriptionRestClient.get()
                .uri(uriBuilder -> uriBuilder.path("pay-availability")
                        .queryParam("renterId", payAvailableRequest.renterId())
                        .queryParam("price", payAvailableRequest.price())
                        .build())
                .retrieve()
                .body(PayAvailableResponse.class);
    }

    public PayResponse pay(PayRequest payAvailableRequest) {
        return subscriptionRestClient.post()
                .uri(uriBuilder -> uriBuilder.path("oina-tokens/credit")
                        .build())
                .body(payAvailableRequest)
                .retrieve()
                .body(PayResponse.class);
    }
}
