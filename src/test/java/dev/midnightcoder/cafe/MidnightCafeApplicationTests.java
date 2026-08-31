package dev.midnightcoder.cafe;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest(classes = MidnightCafeApplication.class)
class MidnightCafeApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void verifiesModulithArchitecture() {
        var modules = ApplicationModules.of(MidnightCafeApplication.class).verify();

        IO.println(modules.toString());
    }
}
