package app.netlify.darlingson.rms_be.repository;

import app.netlify.darlingson.rms_be.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}