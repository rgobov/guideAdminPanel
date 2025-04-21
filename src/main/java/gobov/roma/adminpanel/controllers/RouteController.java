package gobov.roma.adminpanel.controllers;

import gobov.roma.adminpanel.dto.RouteCreateDTO;
import gobov.roma.adminpanel.model.Route;
import gobov.roma.adminpanel.model.Route.TourFormat;
import gobov.roma.adminpanel.services.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @GetMapping
    public ResponseEntity<List<Route>> getRoutes(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) TourFormat format) {
        List<Route> routes = routeService.getRoutesByFilters(city, format);
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Route> getRoute(@PathVariable Long id) {
        Route route = routeService.getRouteById(id);
        return ResponseEntity.ok(route);
    }

    @PostMapping
    public ResponseEntity<Route> createRoute(
            @Valid @RequestBody RouteCreateDTO dto) {
        Route route = routeService.createRoute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(route);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Route> updateRoute(
            @PathVariable Long id,
            @Valid @RequestBody Route route) {
        Route updatedRoute = routeService.updateRoute(id, route);
        return ResponseEntity.ok(updatedRoute);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}