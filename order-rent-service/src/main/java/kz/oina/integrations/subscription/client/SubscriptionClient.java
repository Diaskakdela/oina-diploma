package kz.oina.integrations.subscription.client;

import kz.oina.integrations.subscription.model.PayAvailableRequest;
import kz.oina.integrations.subscription.model.PayAvailableResponse;
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
}
