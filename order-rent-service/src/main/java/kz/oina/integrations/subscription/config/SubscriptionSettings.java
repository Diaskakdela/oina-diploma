package kz.oina.integrations.subscription.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("integration.subscription")
public record SubscriptionSettings(String url) {
}
