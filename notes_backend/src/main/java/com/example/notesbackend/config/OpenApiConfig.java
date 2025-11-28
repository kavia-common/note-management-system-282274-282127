package com.example.notesbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Springdoc OpenAPI configuration with minimal Info.
 * Keeping the definition minimal ensures springdoc emits a valid OpenAPI spec with the top-level "openapi" version field.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Notes Backend API",
                version = "0.1.0",
                description = "CRUD REST API for managing notes."
        )
)
public class OpenApiConfig {
    // Intentionally empty: annotations provide the configuration.
}
