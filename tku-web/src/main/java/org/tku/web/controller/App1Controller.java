package org.tku.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class App1Controller {

    @GetMapping(value = "/note-share/notes")
    public String NotesPage() throws JsonProcessingException {
        return "notes";
    }
}
