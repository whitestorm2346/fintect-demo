package org.tku.api.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.tku.database.entity.User;
import org.tku.database.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
import java.util.Optional;

@RestController
@Log4j2
public class UserController {

    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isAccountExists(String account) {
        log.debug("account: {}", account);

        Optional<User> user = userRepository.findById(account);

        return user.isPresent();
    }

    public void createUser(String account, String password){
        User user = new User();
        user.setAccount(account);
        // 在实际应用中，密码通常应该进行加密处理，以下示例中简化为直接存储明文密码
        user.setPassword(password);

        // 保存用户到数据库
        userRepository.save(user);
    }

    @GetMapping("/api/v1/allUser")
    public ResponseEntity<?> listUser() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/api/v1/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(optionalUser.get());
    }

    @PostMapping("/api/v1/create-account")
    public ResponseEntity<?> createUser(
            String account,
            String new_password,
            String check_password) {
        Optional<User> optionalUser = userRepository.findById(account);
        if (optionalUser.isPresent()) { // account existed
            return ResponseEntity.badRequest().build();
        }

        if(!Objects.equals(new_password, check_password)){ // password does not match
            return ResponseEntity.badRequest().build();
        }

        User createdUser = new User();
        createdUser.setAccount(account);
        createdUser.setPassword(passwordEncoder.encode(new_password));
        userRepository.save(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}