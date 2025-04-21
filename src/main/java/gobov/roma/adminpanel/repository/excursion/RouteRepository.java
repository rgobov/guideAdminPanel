package gobov.roma.adminpanel.repository.excursion;

import gobov.roma.adminpanel.model.Route;
import gobov.roma.adminpanel.model.Route.TourFormat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByCity(String city);
    List<Route> findByTourFormat(TourFormat format);
    List<Route> findByCityAndTourFormat(String city, TourFormat format);
}