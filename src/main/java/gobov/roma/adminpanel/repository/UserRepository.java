package gobov.roma.adminpanel.repository;

import gobov.roma.adminpanel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(entityManagerFactoryRef = "mainEntityManagerFactory")
public interface UserRepository extends JpaRepository<User, Long> {
}