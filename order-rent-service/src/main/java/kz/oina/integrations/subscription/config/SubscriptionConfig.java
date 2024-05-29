package kz.oina.integrations.subscription.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class SubscriptionConfig {
    private final SubscriptionSettings settings;

    @Bean
    public RestClient subscriptionRestClient() {
        return RestClient.builder()
                .baseUrl(settings.url())
                .build();
    }
}
