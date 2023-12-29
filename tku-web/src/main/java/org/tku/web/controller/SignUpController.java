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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.tku.api.controller.UserController;
import org.tku.database.entity.User;
import org.tku.database.repository.UserRepository;
import org.tku.web.entity.SignUpForm;
import org.tku.web.service.UserService;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Controller
@Log4j2
public class SignUpController {
    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    private UserController userController;

    @GetMapping(value = "/sign-up")
    public String signUpPage(Model model) throws JsonProcessingException {
        model.addAttribute("signUpForm", new SignUpForm());
        return "sign_up";
    }

    @PostMapping(value = "/sign-up")
    public String signUp(@ModelAttribute("signUpForm") SignUpForm signUpForm, Model model) {
        log.debug("account = {}, new password = {}, check password = {}",
                signUpForm.getAccount(),
                signUpForm.getNewPassword(),
                signUpForm.getCheckPassword());

        if (!signUpForm.getNewPassword().equals(signUpForm.getCheckPassword())) {
            model.addAttribute("error", "新密码和确认密码不匹配");
            return "redirect:/sign-up";
        }

        if (userController.isAccountExists(signUpForm.getAccount())) {
            model.addAttribute("error", "账号已存在");
            return "redirect:/sign-up";
        }

        userController.createUser(signUpForm.getAccount(), signUpForm.getNewPassword());

        return "redirect:/index";
    }
}