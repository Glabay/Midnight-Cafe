package dev.midnightcoder.cafe.security;

import dev.midnightcoder.cafe.identity.IRegistration;
import dev.midnightcoder.cafe.identity.RegistrationRequest;
import dev.midnightcoder.cafe.identity.UserLookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight-Cafe
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-08-31
 */
@Component
public class UserDataInitializer implements CommandLineRunner {
    private final UserLookup userLookup;
    private final IRegistration registrationService;

    @Value("${dev.midnightcoder.cafe.admin.password}")
    private String ADMIN_PASSWORD;

    UserDataInitializer(UserLookup userLookup, IRegistration registrationService) {
        this.userLookup = userLookup;
        this.registrationService = registrationService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userLookup.findByIdentity("Glabay").isEmpty()) {
            registrationService.register(new RegistrationRequest(
                "Glabay",
                ADMIN_PASSWORD,
                ADMIN_PASSWORD
            ));
        }
    }
}
