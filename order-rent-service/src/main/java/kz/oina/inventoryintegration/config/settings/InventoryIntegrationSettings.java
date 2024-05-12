package kz.oina.inventoryintegration.config.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("integration.inventory")
public record InventoryIntegrationSettings(String url, String apiKey) {
}