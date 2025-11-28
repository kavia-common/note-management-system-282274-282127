package com.example.notesbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PUBLIC_INTERFACE
 * NotesbackendApplication is the entry point for the Spring Boot Notes backend service.
 * It bootstraps the application context and starts the embedded server.
 */
@SpringBootApplication
public class NotesbackendApplication {

    /**
     * PUBLIC_INTERFACE
     * Starts the Spring Boot application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(NotesbackendApplication.class, args);
    }
}
