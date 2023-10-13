package org.tku.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
public class LoginController {
    @Autowired
    LocaleResolver localeResolver;

    @GetMapping("/index")
    public String index(@RequestParam(required = false, defaultValue = "en") String locale,
                        HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        if(locale != null) {
            localeResolver.setLocale(request, response, new Locale(locale));
        }

        return "index";
    }

    @GetMapping(value = {"/login", "/"})
    public String login() throws JsonProcessingException {
        return "login";
    }
}
