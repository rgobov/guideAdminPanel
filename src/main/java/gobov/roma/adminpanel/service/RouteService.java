package gobov.roma.adminpanel.service;

import gobov.roma.adminpanel.model.Route;
import gobov.roma.adminpanel.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route getRouteById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found with id " + id));
    }

    public Route createRoute(Route route) {
        if (route.getTitle() == null || route.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        return routeRepository.save(route);
    }

    public Route updateRoute(Long id, Route updatedRoute) {
        if (updatedRoute.getTitle() == null || updatedRoute.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        return routeRepository.findById(id)
                .map(route -> {
                    route.setTitle(updatedRoute.getTitle());
                    route.setDescription(updatedRoute.getDescription());
                    route.setCity(updatedRoute.getCity());
                    route.setDuration(updatedRoute.getDuration());
                    route.setDistance(updatedRoute.getDistance());
                    route.setType(updatedRoute.getType());
                    route.setCategories(updatedRoute.getCategories());
                    return routeRepository.save(route);
                })
                .orElseThrow(() -> new RuntimeException("Route not found with id " + id));
    }

    public void deleteRoute(Long id) {
        if (!routeRepository.existsById(id)) {
            throw new RuntimeException("Route not found with id " + id);
        }
        routeRepository.deleteById(id);
    }
}