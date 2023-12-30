package org.tku.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.tku.database.entity.User;
import org.tku.database.repository.UserRepository;
import org.tku.web.entity.LoginForm;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Controller
@Log4j2
public class LoginController {
    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = {"/index", "/"})
    public String index(@RequestParam(required = false) String locale,
                        HttpServletRequest request, HttpServletResponse response) {
        if(StringUtils.isNotBlank(locale)) {
            localeResolver.setLocale(request, response, new Locale(locale));
        }
        return "index";
    }
    @GetMapping(value = "/login")
    public String loginPage(Model model) throws JsonProcessingException {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        log.debug("account = {}, password = {}", loginForm.getAccount(), loginForm.getPassword());

        Optional<User> optionalUser = userRepository.findById(loginForm.getAccount());

        if (optionalUser.isEmpty()) { // account existed
            model.addAttribute("error", "This account already existed");
            log.debug("This account already existed");
            return "redirect:/login";
        }

        User user = userRepository.findById(loginForm.getAccount())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with account: " + loginForm.getAccount()));

        if(!Objects.equals(user.getPassword(), loginForm.getPassword())){
            model.addAttribute("error", "Wrong Password");
            log.debug("Wrong password");
            return "redirect:/login";
        }

        log.debug("Login Successfully");

        return "redirect:/index";
    }
}
