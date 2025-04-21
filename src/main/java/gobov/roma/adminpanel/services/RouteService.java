package gobov.roma.adminpanel.services;

import gobov.roma.adminpanel.dto.RouteCreateDTO;
import gobov.roma.adminpanel.model.PointOfInterest;
import gobov.roma.adminpanel.model.Route;
import gobov.roma.adminpanel.model.Route.TourFormat;
import gobov.roma.adminpanel.repository.excursion.RouteRepository;
import gobov.roma.adminpanel.repository.point.PointOfInterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final PointOfInterestRepository pointOfInterestRepository; // Добавлена зависимость

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route getRouteById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found with id " + id));
    }

    @Transactional
    public Route createRoute(RouteCreateDTO dto) {
        validateRouteDTO(dto);

        Route route = Route.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .city(dto.getCity())
                .duration(dto.getDuration())
                .distance(dto.getDistance())
                .categories(dto.getCategories())
                .environmentTypes(dto.getEnvironmentTypes())
                .tourFormat(dto.getTourFormat())
                .build();

        // Привязка точек интереса
        if (dto.getPointIds() != null && !dto.getPointIds().isEmpty()) {
            List<PointOfInterest> points = pointOfInterestRepository.findAllById(dto.getPointIds());
            if (points.size() != dto.getPointIds().size()) {
                throw new IllegalArgumentException("Some points of interest were not found");
            }
            route.setPoints(points);
        }

        return routeRepository.save(route);
    }

    @Transactional
    public Route updateRoute(Long id, Route updatedRoute) {
        validateRoute(updatedRoute);

        return routeRepository.findById(id)
                .map(route -> {
                    route.setTitle(updatedRoute.getTitle());
                    route.setDescription(updatedRoute.getDescription());
                    route.setCity(updatedRoute.getCity());
                    route.setDuration(updatedRoute.getDuration());
                    route.setDistance(updatedRoute.getDistance());
                    route.setCategories(updatedRoute.getCategories());
                    route.setEnvironmentTypes(updatedRoute.getEnvironmentTypes());
                    route.setTourFormat(updatedRoute.getTourFormat());

                    // Обновление точек интереса
                    if (updatedRoute.getPoints() != null) {
                        List<PointOfInterest> points = pointOfInterestRepository.findAllById(
                                updatedRoute.getPoints().stream()
                                        .map(PointOfInterest::getId)
                                        .collect(Collectors.toList())
                        );
                        route.setPoints(points);
                    }

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

    public List<Route> getRoutesByFilters(String city, TourFormat format) {
        if (city != null && format != null) {
            return routeRepository.findByCityAndTourFormat(city, format);
        } else if (city != null) {
            return routeRepository.findByCity(city);
        } else if (format != null) {
            return routeRepository.findByTourFormat(format);
        }
        return routeRepository.findAll();
    }

    private void validateRouteDTO(RouteCreateDTO dto) {
        if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (dto.getEnvironmentTypes() == null || dto.getEnvironmentTypes().isEmpty()) {
            throw new IllegalArgumentException("At least one environment type must be specified");
        }
        if (dto.getTourFormat() == null) {
            throw new IllegalArgumentException("Tour format cannot be null");
        }
    }

    private void validateRoute(Route route) {
        if (route.getTitle() == null || route.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (route.getEnvironmentTypes() == null || route.getEnvironmentTypes().isEmpty()) {
            throw new IllegalArgumentException("At least one environment type must be specified");
        }
        if (route.getTourFormat() == null) {
            throw new IllegalArgumentException("Tour format cannot be null");
        }
    }
}