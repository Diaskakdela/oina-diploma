package kz.oina.integrations.inventory.config;

import kz.oina.integrations.inventory.config.settings.InventoryIntegrationSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class InventoryIntegrationConfig {

    private final InventoryIntegrationSettings inventoryIntegrationSettings;

    @Bean
    public RestClient inventoryItemRestClient() {
        return RestClient.builder()
                .baseUrl(inventoryIntegrationSettings.url())
                .build();
    }
}
