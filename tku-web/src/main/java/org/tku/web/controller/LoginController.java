package org.tku.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
@Log4j2
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
    public String loginPage() throws JsonProcessingException {
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(String userName, String password) throws JsonProcessingException {
        log.debug("userName = {}, password = {}", userName, password);

        return "index";
    }
}
