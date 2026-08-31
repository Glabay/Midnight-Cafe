package dev.midnightcoder.cafe.identity.internal;

import dev.midnightcoder.cafe.identity.IRegistration;
import dev.midnightcoder.cafe.identity.RegistrationRequest;
import dev.midnightcoder.cafe.identity.User;
import dev.midnightcoder.cafe.identity.UserLookup;
import org.springframework.security.crypto.password.PasswordEncoder;
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
class UserService implements UserLookup, IRegistration {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> findByIdentity(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void register(RegistrationRequest request) {
        var user = new User();
            user.setIdentity(request.identity());
            user.setEncryptedPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }
}
