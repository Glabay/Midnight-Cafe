package dev.midnightcoder.cafe.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight-Cafe
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-08-31
 */
@Component
@NullMarked
public class JwtTokenProvider {
    private final SecretKey secretKey;

    @Value("${app.jwt.access-expiration-ms}")
    private Long accessTokenExpiration;

    @Value("${app.jwt.refresh-expiration-ms}")
    private Long refreshTokenExpiration;

    public JwtTokenProvider(
        @Value("${app.jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(UserDetails userDetails) {
        var roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        var date = new Date();
        var expiryDate = new Date(date.getTime() + accessTokenExpiration);

        return Jwts.builder()
            .subject(userDetails.getUsername())
            .claim("roles", roles)
            .issuedAt(date)
            .expiration(expiryDate)
            .signWith(secretKey)
            .compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        var date = new Date();
        var expiryDate = new Date(date.getTime() + refreshTokenExpiration);

        return Jwts.builder()
            .subject(userDetails.getUsername())
            .issuedAt(date)
            .expiration(expiryDate)
            .signWith(secretKey)
            .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
            return true;
        }
        catch (SecurityException | IllegalArgumentException e) {
            return false;
        }
    }
}
