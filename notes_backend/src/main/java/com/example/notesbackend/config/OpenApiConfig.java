package com.example.notesbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * Springdoc OpenAPI configuration and application-level metadata.
 * Ensures that generated /api-docs contains valid OpenAPI 3.x info, title, and version fields.
 *
 * This configuration intentionally avoids defining tags that don't map to actual controllers,
 * which can lead to schema generation issues in certain environments.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Notes Backend API",
                version = "0.1.0",
                description = "CRUD REST API for managing notes. Includes endpoints to create, list, get, update and delete notes.",
                contact = @Contact(name = "Notes Backend Maintainers", email = "support@example.com"),
                license = @License(name = "Apache-2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")
        ),
        servers = {
                @Server(description = "Default Server (respects X-Forwarded headers)", url = "/")
        }
)
public class OpenApiConfig {
    // Intentionally empty: annotations provide the configuration.
}
