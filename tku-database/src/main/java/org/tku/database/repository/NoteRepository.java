package org.tku.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tku.database.entity.Note;

public interface NoteRepository extends JpaRepository<Note, String> {
}