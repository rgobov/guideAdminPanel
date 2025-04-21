package gobov.roma.adminpanel.repository.auth;

import gobov.roma.adminpanel.model.auth.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories(entityManagerFactoryRef = "authEntityManagerFactory")
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
    Optional<Admin> findByEmail(String email);
}