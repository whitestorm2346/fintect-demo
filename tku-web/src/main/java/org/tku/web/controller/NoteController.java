package org.tku.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tku.database.entity.Note;
import org.tku.database.repository.NoteRepository;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/note-share")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping(value = "/notes")
    public String NotesPage(Model model) throws JsonProcessingException {
        List<Note> notes = noteRepository.findByNoteTypeAndAccess("Note", 0);
        log.debug("{}", notes);
        model.addAttribute("publicNotes", notes);
        return "notes";
    }

    @GetMapping(value = "/exercises")
    public String ExercisesPage(Model model) throws JsonProcessingException {
        List<Note> exercises = noteRepository.findByNoteTypeAndAccess("Exercise", 0);
        log.debug("{}", exercises);
        model.addAttribute("publicExercises", exercises);
        return "exercises";
    }

    @GetMapping(value = "/private/notes")
    public String NotesPrivatePage(Model model) throws JsonProcessingException {
        List<Note> notes = noteRepository.findByNoteTypeAndAccess("Note", 0);
        log.debug("{}", notes);
        model.addAttribute("publicNotes", notes);

        List<Note> private_notes = noteRepository.findByNoteTypeAndAccess("Note", 1);
        log.debug("{}", private_notes);
        model.addAttribute("privateNotes", private_notes);
        return "notes";
    }

    @GetMapping(value = "/private/exercises")
    public String ExercisesPrivatePage(Model model) throws JsonProcessingException {
        List<Note> exercises = noteRepository.findByNoteTypeAndAccess("Exercise", 0);
        log.debug("{}", exercises);
        model.addAttribute("publicExercises", exercises);

        List<Note> privtae_exercises = noteRepository.findByNoteTypeAndAccess("Exercise", 1);
        log.debug("{}", privtae_exercises);
        model.addAttribute("privateExercises", privtae_exercises);
        return "exercises";
    }
}
