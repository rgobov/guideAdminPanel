package gobov.roma.adminpanel.repository;

import gobov.roma.adminpanel.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(entityManagerFactoryRef = "mainEntityManagerFactory")
public interface RouteRepository extends JpaRepository<Route, Long> {
}
