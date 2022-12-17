package com.example.tdd_security_second_demo.authentication.rest;

import com.example.tdd_security_second_demo.authentication.domain.User;
import com.example.tdd_security_second_demo.authentication.rest.domain.SignupDto;
import com.example.tdd_security_second_demo.authentication.store.UserStore;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {

    private final UserStore userStore;
    private final PasswordEncoder passwordEncoder;

    public SignupController(UserStore userStore, PasswordEncoder passwordEncoder) {
        this.userStore = userStore;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public String signup(@RequestBody SignupDto signupDto) {
        User signupUser = User.builder()
                .email(signupDto.getEmail())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .build();
        userStore.add(signupUser);
        return signupUser.getId();
    }
}
