package com.example.notesbackend.repository;

import com.example.notesbackend.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Note entity.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
