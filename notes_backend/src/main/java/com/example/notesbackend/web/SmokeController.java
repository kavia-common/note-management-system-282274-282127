package com.example.notesbackend.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PUBLIC_INTERFACE
 * Simple smoke endpoint to verify the application is running and to help OpenAPI generation.
 */
@RestController
@Tag(name = "Smoke", description = "Simple health-like endpoint")
public class SmokeController {

    // PUBLIC_INTERFACE
    @GetMapping("/api/smoke")
    /** Returns a simple string confirming the app is running. */
    @Operation(summary = "Smoke check", description = "Returns a basic message to confirm the service is up.")
    public String smoke() {
        return "ok";
    }
}
