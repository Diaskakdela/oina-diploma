package kz.oina.toyintegration.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class ToyConfig {
    private final ToyIntegrationSettings toyIntegrationSettings;

    @Bean
    public RestClient toyRestClient() {
        return RestClient.builder()
                .baseUrl(toyIntegrationSettings.url())
                .build();
    }
}
