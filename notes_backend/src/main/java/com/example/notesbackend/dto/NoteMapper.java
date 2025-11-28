package com.example.notesbackend.dto;

import com.example.notesbackend.domain.Note;

/**
 * Utility mapper for converting between Note entity and DTOs.
 */
public final class NoteMapper {

    private NoteMapper() {}

    // PUBLIC_INTERFACE
    public static NoteResponse toResponse(Note note) {
        /** Convert Note entity to NoteResponse DTO. */
        return new NoteResponse()
                .setId(note.getId())
                .setTitle(note.getTitle())
                .setContent(note.getContent())
                .setCreatedAt(note.getCreatedAt())
                .setUpdatedAt(note.getUpdatedAt());
    }
}
