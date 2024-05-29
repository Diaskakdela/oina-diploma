package kz.oina.integrations.inventory.config.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("integration.inventory")
public record InventoryIntegrationSettings(String url) {
}
