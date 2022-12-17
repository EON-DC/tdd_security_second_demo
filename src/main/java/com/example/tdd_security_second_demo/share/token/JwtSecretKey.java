package com.example.tdd_security_second_demo.share.token;

public class JwtSecretKey {

    private final byte[] secretKey;

    public JwtSecretKey(String keySource) {
        secretKey = keySource.getBytes();
    }

    public byte[] getSecretKeyAsByteCode() {
        return secretKey;
    }
}
