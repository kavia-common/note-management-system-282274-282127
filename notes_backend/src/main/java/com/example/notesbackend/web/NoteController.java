package com.example.notesbackend.web;

import com.example.notesbackend.domain.Note;
import com.example.notesbackend.dto.NoteMapper;
import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * REST controller exposing CRUD operations for notes.
 * Endpoints:
 *  - POST /api/notes -> create a note
 *  - GET /api/notes -> list notes (optional page,size)
 *  - GET /api/notes/{id} -> get single note
 *  - PUT /api/notes/{id} -> update note
 *  - DELETE /api/notes/{id} -> delete note
 */
@RestController
@RequestMapping("/api/notes")
@Tag(name = "Notes", description = "CRUD operations for Notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    // PUBLIC_INTERFACE
    @PostMapping
    @Operation(
            summary = "Create a new note",
            description = "Creates a note with title and content.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Validation error")
            }
    )
    public ResponseEntity<NoteResponse> create(@Valid @RequestBody NoteRequest request,
                                               UriComponentsBuilder uriBuilder) {
        /** Create a note and return 201 with Location header and body. */
        Note created = service.createNote(request);
        NoteResponse body = NoteMapper.toResponse(created);
        URI location = uriBuilder.path("/api/notes/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(body);
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(
            summary = "List notes",
            description = "Returns a paginated list of notes. Defaults to page=0,size=20 if not provided.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK")
            }
    )
    public ResponseEntity<List<NoteResponse>> list(@RequestParam(value = "page", required = false) Integer page,
                                                   @RequestParam(value = "size", required = false) Integer size,
                                                   @RequestParam(value = "includePageMetadata", required = false, defaultValue = "false") boolean includePageMetadata) {
        /** List notes with optional pagination. */
        Page<Note> result = service.listNotes(page, size);
        List<NoteResponse> responses = result.getContent().stream().map(NoteMapper::toResponse).toList();

        if (includePageMetadata) {
            // If needed, could return a wrapper with pagination metadata. For simplicity, return just list by default.
        }

        return ResponseEntity.ok(responses);
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(
            summary = "Get a note by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<NoteResponse> getById(@PathVariable("id") Long id) {
        /** Retrieve a note by ID. */
        Note note = service.getNoteById(id);
        return ResponseEntity.ok(NoteMapper.toResponse(note));
    }

    // PUBLIC_INTERFACE
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a note",
            description = "Updates a note's title and content.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Validation error"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<NoteResponse> update(@PathVariable("id") Long id, @Valid @RequestBody NoteRequest request) {
        /** Update a note by ID. */
        Note updated = service.updateNote(id, request);
        return ResponseEntity.ok(NoteMapper.toResponse(updated));
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a note",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public void delete(@PathVariable("id") Long id) {
        /** Delete a note by ID. */
        service.deleteNote(id);
    }
}
