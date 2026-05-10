package com.example.mspractice.demo.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${external.api.base-url}")
    private String baseUrl;

    @Value("${external.api.username}")
    private String username;

    @Value("${external.api.password}")
    private String password;

    @Value("${token.api.base-url}")
    private String tokenUrl;

    @Value("${token.api.key}")
    private String xApiKey;

//    @Bean
//    public WebClient webClient() {
//        return WebClient.builder()
//                .baseUrl(baseUrl)
//                .defaultHeaders(headers ->
//                        headers.setBasicAuth(username, password))
//                .build();
//    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(tokenUrl)
                .defaultHeader("x-api-key", xApiKey)
                .build();
    }
}
