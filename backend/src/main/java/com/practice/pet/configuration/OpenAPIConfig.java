package com.practice.pet.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                description = "Это API предоставляет эндпоинты для TODO листа.",
                title = "Системное API для TODO листа.",
                version = "1.0"
        ),
        servers = {
                @io.swagger.v3.oas.annotations.servers.Server(
                        description = "Local ENV",
                        url = "http://localhost:8092"
                )
        }
)
public class OpenAPIConfig {
}
