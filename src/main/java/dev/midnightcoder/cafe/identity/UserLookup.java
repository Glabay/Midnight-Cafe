package dev.midnightcoder.cafe.identity;

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
public interface UserLookup {
    Optional<User> findByIdentity(String username);
    Optional<User> findById(UUID id);

    List<User> findAll();
}
