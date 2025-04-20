package gobov.roma.adminpanel.repository;

import gobov.roma.adminpanel.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(entityManagerFactoryRef = "mainEntityManagerFactory")
public interface MediaRepository extends JpaRepository<Media, Long> {
}