package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // Security scheme name
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                // API Info
                .info(new Info()
                        .title("JWT Demo API")
                        .version("1.0")
                        .description("Simple JWT Demo Project for Students"))

                // Server Configuration
                .servers(List.of(
                        new Server().url("https://9020.pro604cr.amypo.ai")
                ))

                // 🔐 Apply JWT security globally
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))

                // 🔐 Define Bearer Auth
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Enter JWT token WITHOUT 'Bearer ' prefix")));
    }
}
