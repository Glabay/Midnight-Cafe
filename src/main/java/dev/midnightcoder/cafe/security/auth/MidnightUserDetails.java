package dev.midnightcoder.cafe.security.auth;

import dev.midnightcoder.cafe.identity.User;
import jakarta.annotation.Nullable;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight-Cafe
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-08-31
 */
@NullMarked
public record MidnightUserDetails(
    User user
) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user().getRoleNames().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role)))
            .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    @Nullable
    public String getPassword() {
        return user().getEncryptedPassword();
    }

    @Override
    public String getUsername() {
        return user().getUsername();
    }

    @Override
    public boolean isEnabled() {
        return user().isEnabled();
    }
}
