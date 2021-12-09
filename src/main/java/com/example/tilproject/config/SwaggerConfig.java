package com.example.tilproject.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi openApi(){
        return GroupedOpenApi.builder()
                .group("til.shop")
                .pathsToMatch("/api/**")
                .packagesToScan("com.example.tilproject.controller")
                .build();
    }
}
