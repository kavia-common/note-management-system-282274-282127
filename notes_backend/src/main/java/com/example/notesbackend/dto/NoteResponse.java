package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

/**
 * DTO representing a Note in API responses.
 */
public class NoteResponse {

    @Schema(description = "Unique identifier of the note", example = "1")
    private Long id;

    @Schema(description = "Title of the note", example = "Meeting notes")
    private String title;

    @Schema(description = "Content/body of the note", example = "Discuss project milestones...")
    private String content;

    @Schema(description = "Creation timestamp in UTC")
    private Instant createdAt;

    @Schema(description = "Last update timestamp in UTC")
    private Instant updatedAt;

    public NoteResponse() {}

    public Long getId() {
        return id;
    }

    public NoteResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NoteResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NoteResponse setContent(String content) {
        this.content = content;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public NoteResponse setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public NoteResponse setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
