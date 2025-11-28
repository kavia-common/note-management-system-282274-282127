package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO for creating or updating a Note.
 */
public class NoteRequest {

    @NotBlank(message = "Title must not be blank")
    @Schema(description = "Title of the note", example = "Meeting notes")
    private String title;

    @Schema(description = "Content/body of the note", example = "Discuss project milestones...")
    private String content;

    public NoteRequest() {}

    public NoteRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public NoteRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NoteRequest setContent(String content) {
        this.content = content;
        return this;
    }
}
