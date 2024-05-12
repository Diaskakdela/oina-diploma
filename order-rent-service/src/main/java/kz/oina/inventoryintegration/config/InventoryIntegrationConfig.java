package kz.oina.inventoryintegration.config;

import kz.oina.inventoryintegration.config.settings.InventoryIntegrationSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class InventoryIntegrationConfig {
    private static final String API_KEY_HEADER_NAME = "X-API-KEY";

    private final InventoryIntegrationSettings inventoryIntegrationSettings;

    @Bean
    public RestClient inventoryItemRestClient() {
        return RestClient.builder()
                .baseUrl(inventoryIntegrationSettings.url())
                .defaultHeaders(httpHeaders -> httpHeaders.add(API_KEY_HEADER_NAME, inventoryIntegrationSettings.apiKey()))
                .build();
    }
}
