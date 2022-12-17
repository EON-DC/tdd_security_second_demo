package com.example.tdd_security_second_demo.share.token;

import com.example.tdd_security_second_demo.authentication.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

public class JwtTokenGenerator implements TokenGenerator {

    private final JwtSecretKey jwtSecretKey;
    private final static long MINUTE = 60 * 1000L;
    private final static long ACCESS_TOKEN_VALID_TIME = 10 * MINUTE;
    private final static long REFRESH_TOKEN_VALID_TIME = 30 * MINUTE;

    public JwtTokenGenerator(JwtSecretKey jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

    @Override
    public String createAccessToken(String email, List<String> roles) {
        return createToken(email, roles, ACCESS_TOKEN_VALID_TIME);
    }

    @Override
    public String createRefreshToken(String email, List<String> roles) {
        return createToken(email, roles, REFRESH_TOKEN_VALID_TIME);
    }

    private String createToken(String email, List<String> roles, long validTime) {
        Claims claims = Jwts.claims();
        claims.put("roles", roles);
        Date now = new Date();
        String token = Jwts.builder()
                .setSubject(email)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validTime))
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey.getSecretKeyAsByteCode())
                .compact();
        return token;
    }
}
