package org.tku.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.tku.database.entity.User;
import org.tku.database.repository.UserRepository;
import org.tku.web.entity.LoginForm;
import org.tku.web.entity.SignUpForm;

import java.util.Objects;
import java.util.Optional;

@Controller
@Log4j2
public class SignUpController {
    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = "/sign-up")
    public String signUpPage(Model model) throws JsonProcessingException {
        model.addAttribute("signUpForm", new SignUpForm());
        return "sign_up";
    }

    @PostMapping(value = "/create-account")
    public String signUp(@ModelAttribute("signUpForm") SignUpForm signUpForm, Model model) {
        log.debug("account = {}, new password = {}, check password = {}",
                signUpForm.getAccount(),
                signUpForm.getNewPassword(),
                signUpForm.getCheckPassword());

        if (!signUpForm.getNewPassword().equals(signUpForm.getCheckPassword())) {
            model.addAttribute("error", "新密码和确认密码不匹配");
            log.debug("Passwords do not match: new password = {}, check password = {}",
                    signUpForm.getNewPassword(), signUpForm.getCheckPassword());
            return "redirect:/sign-up";
        }

        Optional<User> optionalUser = userRepository.findById(signUpForm.getAccount());
        if (optionalUser.isPresent()) { // account existed
            model.addAttribute("error", "账号已存在");
            log.debug("This account already existed");
            return "redirect:/sign-up";
        }

        User user = new User();

        user.setAccount(signUpForm.getAccount());
        log.debug("before encoding: {}", signUpForm.getNewPassword());
        user.setPassword(passwordEncoder.encode(signUpForm.getNewPassword()));
        log.debug("after encoding: {}", user.getPassword());

        userRepository.save(user);

        return "redirect:/login";
    }
}