package dev.midnightcoder.cafe.security.auth;

import dev.midnightcoder.cafe.security.jwt.JwtTokenProvider;
import dev.midnightcoder.cafe.security.jwt.TokenRefreshRequest;
import dev.midnightcoder.cafe.security.jwt.TokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight-Cafe
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-08-28
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthController(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService customUserDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        var refreshToken = request.refreshToken();
        if (jwtTokenProvider.validateToken(refreshToken)) {
            var username = jwtTokenProvider.extractUsername(refreshToken);
            var user = customUserDetailsService.loadUserByUsername(username);
            var accessToken = jwtTokenProvider.generateAccessToken(user);
            var refresh = jwtTokenProvider.generateRefreshToken(user);
            return ResponseEntity.ok(new TokenResponse(accessToken, refresh));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
