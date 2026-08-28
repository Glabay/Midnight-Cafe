package dev.midnightcoder.cafe.identity;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight-Cafe
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-08-28
 */
public record RegistrationRequest(
    @NotBlank(message = "Identity is required")
    String identity,
    @NotBlank(message = "Password is required")
    String password,
    @NotBlank(message = "Re-entered password is required")
    String rePassword
) {}
