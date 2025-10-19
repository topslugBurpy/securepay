package com.example.demo.SpringSecurity;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;


import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey key;
    private final long ttlMillies;

    public JwtService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.ttl-hours}") long ttlHours) {
        if(secret == null || secret.isEmpty() || secret.length() < 32) {
            throw new IllegalArgumentException("secret cannot be null or empty and should be at least 32 characters");
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.ttlMillies = ttlHours*60*60*1000;

    }

    //generates the JWT token
    public String generateToken(String username) {
        Instant now = Instant.now();
        return Jwts.builder().subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(ttlMillies)))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }
}
