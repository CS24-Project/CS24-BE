package com.example.csdaily.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Base64;

@Component
public class JwtTokenProvider {
    private final Key SECRET_KEY;
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24시간

    public JwtTokenProvider() {
        // 임시 key 설정 (변경 예정)
        String base64Secret = Base64.getEncoder().encodeToString("your-very-secure-secret-key-must-be-long-enough".getBytes(StandardCharsets.UTF_8));
        this.SECRET_KEY = Keys.hmacShaKeyFor(base64Secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}
