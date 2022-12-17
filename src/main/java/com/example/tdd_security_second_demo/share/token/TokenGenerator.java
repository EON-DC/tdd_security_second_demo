package com.example.tdd_security_second_demo.share.token;

import java.util.List;

public interface TokenGenerator {
    String createAccessToken(String email, List<String> roles);
    String createRefreshToken(String email, List<String> roles);
}
