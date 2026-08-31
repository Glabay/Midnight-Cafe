package dev.midnightcoder.cafe.security.jwt;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight-Cafe
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-08-31
 */
public record TokenResponse(
    String accessToken,
    String refreshToken,
    String tokenType
) {
    public TokenResponse(String accessToken, String refreshToken) {
        this(accessToken, refreshToken, "Bearer");
    }
}
