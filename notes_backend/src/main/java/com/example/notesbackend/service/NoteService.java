package com.example.notesbackend.service;

import com.example.notesbackend.domain.Note;
import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.exception.NotFoundException;
import com.example.notesbackend.repository.NoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service containing business logic for Notes.
 */
@Service
public class NoteService {

    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    // PUBLIC_INTERFACE
    public Note createNote(NoteRequest request) {
        /** Create and persist a new Note from the given request. */
        Note note = new Note(request.getTitle(), request.getContent());
        return repository.save(note);
    }

    // PUBLIC_INTERFACE
    public Note getNoteById(Long id) {
        /** Retrieve a note by ID or throw NotFoundException. */
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Note with id " + id + " not found")
        );
    }

    // PUBLIC_INTERFACE
    public Page<Note> listNotes(Integer page, Integer size) {
        /** List notes with optional pagination. Defaults to page=0,size=20 if null. */
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 20);
        return repository.findAll(pageable);
    }

    // PUBLIC_INTERFACE
    public Note updateNote(Long id, NoteRequest request) {
        /** Update an existing note's title and content. */
        Note existing = getNoteById(id);
        existing.setTitle(request.getTitle());
        existing.setContent(request.getContent());
        return repository.save(existing);
    }

    // PUBLIC_INTERFACE
    public void deleteNote(Long id) {
        /** Delete note by ID or throw if missing. */
        Note existing = getNoteById(id);
        repository.delete(existing);
    }
}
