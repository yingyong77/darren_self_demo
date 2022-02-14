package com.darren.demo.db.redis;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate config
 *
 * @author Darren.
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
