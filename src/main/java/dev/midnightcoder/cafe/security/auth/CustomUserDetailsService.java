package dev.midnightcoder.cafe.security.auth;

import dev.midnightcoder.cafe.identity.UserLookup;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight-Cafe
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-08-28
 */
@Service
@NullMarked
class CustomUserDetailsService implements UserDetailsService {
    private final UserLookup userLookup;

    CustomUserDetailsService(UserLookup userLookup) {
        this.userLookup = userLookup;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var cachedUser = userLookup.findByIdentity(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(
            cachedUser.getUsername(),
            cachedUser.getEncryptedPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
