package com.kobe.signUpApp.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    // 로깅을 위한 Logger 객체 생성.
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private static final String SECRET_KEY = "sUL8l4EpgcyFOOi2h+8D49oEWaK4rvIRrpFiCtKdgpoHudZASvVftmJ6x9XHIN8FzPOzQj/4iCzzBh2ONIv0Ww==";
    private static final long EXPIRATION_TIME = 86400000; // 1일

    private static SecretKey getSigningKey() {
        byte[] keyBytes = io.jsonwebtoken.io.Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT 토큰 생성 메서드
    public static String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // JWT 토큰 검증 및 파싱 메서드
    public static String validateTokenAndGetEmail(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (JwtException e) {
            // 예외를 로깅
            logger.error("JWT validation failed: {}", e.getMessage(), e);
            return null;
        }
    }
}
