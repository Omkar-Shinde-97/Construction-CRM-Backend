package com.buildcrm.config;

import com.buildcrm.entity.UserEntity;
import com.buildcrm.enums.UserRole;
import com.buildcrm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app.seed.admin", name = "enabled", havingValue = "true")
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.seed.admin.username:admin}")
    private String username;

    @Value("${app.seed.admin.email:admin@buildcrm.local}")
    private String email;

    @Value("${app.seed.admin.password:admin123}")
    private String password;

    @Value("${app.seed.admin.full-name:Admin User}")
    private String fullName;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsernameAndDeletedFalse(username).isPresent()) {
            return;
        }

        UserEntity admin = UserEntity.builder()
            .id(UUID.randomUUID())
            .username(username)
            .email(email)
            .password(passwordEncoder.encode(password))
            .fullName(fullName)
            .role(UserRole.SUPER_ADMIN)
            .active(true)
            .build();

        userRepository.save(admin);
    }
}
