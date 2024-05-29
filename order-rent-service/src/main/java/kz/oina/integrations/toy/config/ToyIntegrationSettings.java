package kz.oina.integrations.toy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("integration.toys-service")
public record ToyIntegrationSettings(String url) {
}
