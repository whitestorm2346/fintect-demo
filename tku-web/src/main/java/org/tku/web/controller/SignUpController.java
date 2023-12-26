package org.tku.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
@Log4j2
public class SignUpController {
    @Autowired
    LocaleResolver localeResolver;

    @GetMapping(value = "/sign-up")
    public String loginPage() throws JsonProcessingException {
        return "sign_up";
    }

    @PostMapping(value = "/sign-up")
    public String login(String userName, String password) {
        log.debug("userName = {}, password = {}", userName, password);
        return "redirect:/index";
    }
}