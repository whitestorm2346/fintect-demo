package org.tku.database.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tku.database.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, String> {
}