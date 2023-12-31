package org.tku.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tku.database.entity.Note;
import org.tku.database.repository.NoteRepository;

import java.util.List;

@Controller
@RequestMapping("/notes-share")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping(value = "/notes")
    public String NotesPage(Model model) throws JsonProcessingException {
        List<Note> notes = noteRepository.findByTypeAndAccess("Note", 0);
        model.addAttribute("publicNotes", notes);
        return "notes";
    }

    @GetMapping(value = "/exercises")
    public String ExercisesPage(Model model) throws JsonProcessingException {
        List<Note> exercises = noteRepository.findByTypeAndAccess("Exercise", 0);
        model.addAttribute("publicExercises", exercises);
        return "exercises";
    }
}
