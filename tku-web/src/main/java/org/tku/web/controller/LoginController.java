package org.tku.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
<<<<<<< HEAD
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
=======
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
>>>>>>> 160555354690aba076bc88dbc4004f3e95840c44
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
=======
import java.util.Locale;
>>>>>>> 160555354690aba076bc88dbc4004f3e95840c44

@Controller
@Log4j2
public class LoginController {
    @Autowired
    LocaleResolver localeResolver;

    @GetMapping("/index")
<<<<<<< HEAD
    public String index(@RequestParam(required = false) String locale,
                        HttpServletRequest request, HttpServletResponse response) {
        if(StringUtils.isNotBlank(locale)) {
            localeResolver.setLocale(request, response, new Locale(locale));
        }
=======
    public String index(@RequestParam(required = false, defaultValue = "en") String locale,
                        HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        if(locale != null) {
            localeResolver.setLocale(request, response, new Locale(locale));
        }

>>>>>>> 160555354690aba076bc88dbc4004f3e95840c44
        return "index";
    }
    @GetMapping(value = {"/login", "/"})
    public String loginPage() throws JsonProcessingException {
        return "login";
    }

    @PostMapping(value = "/login")
<<<<<<< HEAD
    public String login(String userName, String password) {
        log.debug("userName = {}, password = {}", userName, password);
        return "redirect:/index";
=======
    public String login(String userName, String password) throws JsonProcessingException {
        log.debug("userName = {}, password = {}", userName, password);

        return "index";
>>>>>>> 160555354690aba076bc88dbc4004f3e95840c44
    }
}
