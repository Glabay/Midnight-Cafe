package dev.midnightcoder.cafe.identity.internal;

import dev.midnightcoder.cafe.identity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u FROM User u WHERE u.identity = ?1")
    Optional<User> findByUsername(String username);
}