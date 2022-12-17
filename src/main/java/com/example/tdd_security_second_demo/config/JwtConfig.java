package com.example.tdd_security_second_demo.config;

import com.example.tdd_security_second_demo.share.token.JwtSecretKey;
import com.example.tdd_security_second_demo.share.token.JwtTokenGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {


    @Bean
    public JwtSecretKey getJwtSecretKey() {
        return new JwtSecretKey("serverSecretKey");
    }

    @Bean
    public JwtTokenGenerator tokenGenerator() {
        return new JwtTokenGenerator(getJwtSecretKey());
    }
}
