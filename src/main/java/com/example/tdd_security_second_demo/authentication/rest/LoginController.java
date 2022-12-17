package com.example.tdd_security_second_demo.authentication.rest;

import com.example.tdd_security_second_demo.authentication.domain.User;
import com.example.tdd_security_second_demo.authentication.rest.domain.LoginDto;
import com.example.tdd_security_second_demo.authentication.rest.domain.TokenResponse;
import com.example.tdd_security_second_demo.authentication.service.UserDefaultService;
import com.example.tdd_security_second_demo.share.token.JwtTokenGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserDefaultService userDefaultService;
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenGenerator tokenGenerator;

    public LoginController(UserDefaultService userDefaultService, PasswordEncoder passwordEncoder, JwtTokenGenerator tokenGenerator) {
        this.userDefaultService = userDefaultService;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping
    public TokenResponse login(@RequestBody LoginDto loginDto) {
        User user = userDefaultService.loadUserByUsername(loginDto.getEmail());// (inner) invalid pw -> throw ex
        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return new TokenResponse(
                    tokenGenerator.createAccessToken(user.getUsername(), user.getUserRoleAsString()), //accessToken
                    tokenGenerator.createRefreshToken(user.getUsername(), user.getUserRoleAsString())// refreshToken
            );
        }
        return null;
    }
}
