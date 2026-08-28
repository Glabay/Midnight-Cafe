package dev.midnightcoder.cafe.identity.internal;

import dev.midnightcoder.cafe.identity.UserLookup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight-Cafe
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-08-28
 */
@Service
class UserService implements UserLookup {


    @Override
    public Optional<User> findByIdentity(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }
}
