package ch.martinelli.demo.vaadin.repository;

import ch.martinelli.demo.vaadin.models.ERole;
import ch.martinelli.demo.vaadin.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
