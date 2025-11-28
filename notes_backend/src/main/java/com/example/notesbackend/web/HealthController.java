package com.example.notesbackend.web;

import com.example.notesbackend.repository.NoteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * PUBLIC_INTERFACE
 * Lightweight health endpoint for diagnostics.
 * Always returns 200 OK with status UP, even if DB check fails.
 */
@RestController
@Tag(name = "Health", description = "Application health diagnostics")
public class HealthController {

    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);
    private final NoteRepository noteRepository;

    public HealthController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // PUBLIC_INTERFACE
    @GetMapping("/health")
    @Operation(
            summary = "Health check endpoint",
            description = "Returns application health status. Always returns UP even if optional DB check fails."
    )
    /**
     * Returns health status with optional DB connectivity check.
     * This endpoint never fails - it always returns 200 OK with status UP.
     * DB errors are logged but don't affect the response.
     */
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        
        // Optional DB check - wrapped in try/catch to ensure endpoint never fails
        Map<String, String> dbHealth = new HashMap<>();
        try {
            long count = noteRepository.count();
            dbHealth.put("status", "UP");
            dbHealth.put("notes_count", String.valueOf(count));
            logger.debug("Health check: DB connection successful, note count={}", count);
        } catch (Exception e) {
            dbHealth.put("status", "DOWN");
            dbHealth.put("error", "DB check failed");
            logger.warn("Health check: DB connection failed but returning UP status. error={} message={}", 
                    e.getClass().getSimpleName(), e.getMessage());
        }
        
        response.put("database", dbHealth);
        return ResponseEntity.ok(response);
    }
}
