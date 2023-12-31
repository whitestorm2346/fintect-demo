package org.tku.database.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tku.database.entity.Note;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, String> {
    List<Note> findByType(String type);
    List<Note> findByAccess(Integer access);
    List<Note> findByTypeAndAccess(String type, int access);
}