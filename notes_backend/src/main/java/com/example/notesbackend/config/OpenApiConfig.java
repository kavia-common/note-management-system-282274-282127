package com.example.notesbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * Springdoc OpenAPI configuration and application-level metadata.
 * Ensures that generated /api-docs contains valid OpenAPI 3.x info, title, and version fields.
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
        },
        tags = {
                @Tag(name = "Notes", description = "CRUD operations for Notes"),
                @Tag(name = "Hello Controller", description = "Basic endpoints for notesbackend")
        }
)
// Note: No security scheme required, but an example could be defined like below if needed.
// @SecurityScheme(name = "bearerAuth", scheme = "bearer", type = io.swagger.v3.oas.annotations.security.SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, bearerFormat = "JWT")
public class OpenApiConfig {
    // Intentionally empty: annotations provide the configuration.
}
