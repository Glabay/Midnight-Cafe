package dev.midnightcoder.cafe.identity.internal;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight-Cafe
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-08-28
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String identity;
    private String encryptedPassword;

    private Boolean enabled;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public String getUsername() {
        return identity;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
}
