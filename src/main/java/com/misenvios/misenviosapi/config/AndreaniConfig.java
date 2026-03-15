package com.misenvios.misenviosapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AndreaniConfig {

    public static final String API_KEY  = "12345678901234567890123456789012"; // 32 bytes → AES-256
    public static final String API_IV   = "1234567890123456";                 // 16 bytes → CBC IV
    public static final String BASE_URL = "https://tracking-api.andreani.com/api/";
    public static final String BASE_URL_ULTIMA_MILLA = "https://tracking-api-ultima-milla.andreani.com";

    @Bean
    public WebClient andreaniWebClient() {
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("x-api-key", API_KEY)
                .defaultHeader("Origin", "https://www.andreani.com")
                .defaultHeader("Referer", "https://www.andreani.com/")
                .defaultHeader("Accept", "application/json")
                .defaultHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
