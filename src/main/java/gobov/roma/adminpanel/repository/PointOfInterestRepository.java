package gobov.roma.adminpanel.repository;

import gobov.roma.adminpanel.model.PointOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories(entityManagerFactoryRef = "mainEntityManagerFactory")
public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {
    @Query(value = "SELECT * FROM points_of_interest WHERE ST_DWithin(location, ST_SetSRID(ST_MakePoint(:lng, :lat), 4326), :radius)", nativeQuery = true)
    List<PointOfInterest> findNearbyPoints(@Param("lat") double lat, @Param("lng") double lng, @Param("radius") double radius);
}