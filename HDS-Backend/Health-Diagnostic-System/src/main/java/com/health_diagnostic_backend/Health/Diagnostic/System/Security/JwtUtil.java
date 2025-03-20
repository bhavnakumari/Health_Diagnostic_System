package com.health_diagnostic_backend.Health.Diagnostic.System.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

//Handles JWT token creation, validation, and extraction
@Component
public class JwtUtil {

    // Read secret key from application.properties
    @Value("${jwt.secret}")
    private String secret;

    // Convert Base64 encoded key into a SecretKeySpec object
    private Key getSigningKey() {
        return new SecretKeySpec(Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName());
    }

    //Generates a JWT Token for a given Username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
                .signWith(getSigningKey()) // Secure signing key
                .compact();
    }

    // Extract Username from JWT
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // validates a token if it matches the given username
    public boolean validateToken(String token,String username){
        return extractUsername(token).equals(username);
    }
}
