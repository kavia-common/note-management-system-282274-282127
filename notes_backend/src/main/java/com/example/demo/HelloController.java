package com.example.notesbackend.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Basic endpoints for welcome/info/health.
 * Placed under com.example.notesbackend.* to be picked up by component scanning from the main application.
 */
@RestController
@Tag(name = "Hello Controller", description = "Basic endpoints for notesbackend")
public class HelloController {

    // PUBLIC_INTERFACE
    @GetMapping("/")
    /** Welcome endpoint - Returns a welcome message */
    public String hello() {
        return "Hello, Spring Boot! Welcome to notesbackend";
    }

    // PUBLIC_INTERFACE
    @GetMapping("/docs")
    /** API Documentation redirect - Redirects to Swagger UI preserving original scheme/host/port */
    public RedirectView docs(HttpServletRequest request) {
        // Build an absolute URL based on the incoming request, honoring X-Forwarded-* headers
        String target = UriComponentsBuilder
                .fromHttpRequest(new ServletServerHttpRequest(request))
                .replacePath("/swagger-ui.html")
                .replaceQuery(null)
                .build()
                .toUriString();

        RedirectView rv = new RedirectView(target);
        // Use HTTP 1.1 compatible redirects when necessary (preserves 303/307 semantics if used)
        rv.setHttp10Compatible(false);
        return rv;
    }

    // PUBLIC_INTERFACE
    @GetMapping("/health")
    /** Health check - Returns application health status */
    public String health() {
        return "OK";
    }

    // PUBLIC_INTERFACE
    @GetMapping("/api/info")
    /** Application info - Returns application information */
    public String info() {
        return "Spring Boot Application: notesbackend";
    }
}