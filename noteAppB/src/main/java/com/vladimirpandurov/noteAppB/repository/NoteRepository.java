package com.vladimirpandurov.noteAppB.repository;

import com.vladimirpandurov.noteAppB.domain.Note;
import com.vladimirpandurov.noteAppB.enumeration.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByLevel(Level level);
    void deleteNoteById(Long id);
}
