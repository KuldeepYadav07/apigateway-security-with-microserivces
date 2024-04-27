package com.gateway.servicegateway.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Resilience4JConfig {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        log.info("Inside defaultCustomizer method from Resilience4JConfig ");
        return factory -> factory.configureDefault(
                id -> new Resilience4JConfigBuilder(id)
                        .circuitBreakerConfig(
                                CircuitBreakerConfig.ofDefaults()).build());
    }

}