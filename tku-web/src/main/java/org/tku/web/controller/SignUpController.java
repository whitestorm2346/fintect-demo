package org.tku.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.tku.database.entity.User;
import org.tku.database.repository.UserRepository;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Controller
@Log4j2
public class SignUpController {
    @Autowired
    LocaleResolver localeResolver;

    @GetMapping(value = "/sign-up")
    public String signUpPage() throws JsonProcessingException {
        return "sign_up";
    }

    @PostMapping(value = "/sign-up")
    public String signUp(String userName, String password) {
        log.debug("userName = {}, password = {}", userName, password);
        return "redirect:/index";
    }

    @PostMapping(value = "/create-account")
    public String createAccount(
        @RequestParam String account,
        @RequestParam String new_password,
        @RequestParam String check_password
    ) {
        log.debug("account = {}, new password = {}, check password = {}", account, new_password, check_password);

        return "redirect:/index";
    }

}