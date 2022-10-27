package ch.martinelli.demo.vaadin;

import ch.martinelli.demo.vaadin.models.Role;
import ch.martinelli.demo.vaadin.models.User;
import ch.martinelli.demo.vaadin.repository.RoleRepository;
import ch.martinelli.demo.vaadin.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static ch.martinelli.demo.vaadin.models.ERole.ROLE_USER;

@Component
class UserCreator implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    UserCreator(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (!userRepository.existsByEmail("john.doe@mail.com")) {
            Role role = new Role(ROLE_USER);
            role = roleRepository.save(role);

            User user = new User("john", "john.doe@mail.com", passwordEncoder.encode("password"));
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }
}
