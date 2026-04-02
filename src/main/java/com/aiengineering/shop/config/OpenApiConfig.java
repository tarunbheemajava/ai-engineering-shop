package com.aiengineering.shop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .info(new Info()
                .title("AI Engineering Shop API")
                .description("REST API for the AI Engineering Shop")
                .version("0.0.1-SNAPSHOT")
                .contact(new Contact()
                    .name("Tarun Bheema")
                    .email("tarun.bheema.java@gmail.com")));
    }
}
