package com.example.visites.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info().title("Tracking Application")
                        .description("Backend APIs pour une application de Tacking Visites")
                        .version("1.0")
                        .contact(new Contact().name("Donfack Duval").email("donfackduval@gmail.com"))
                        .license(new License().name("License").url("/")))
                .externalDocs(new ExternalDocumentation().description("Tracking App Documentation")
                        .url("http://localhost:9080/api/word23" +
                                "swagger-ui/index.html"));
    }

}
