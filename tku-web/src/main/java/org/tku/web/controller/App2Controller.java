package org.tku.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class App2Controller {

    @GetMapping(value = "/note-share/exercises")
    public String ExercisesPage() throws JsonProcessingException {
        return "exercises";
    }
}
