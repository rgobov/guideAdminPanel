package gobov.roma.adminpanel.controllers;

import gobov.roma.mvpguide.dto.RouteCreateDTO;
import gobov.roma.mvpguide.model.Route;
import gobov.roma.mvpguide.services.RouteService;
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
            @RequestParam(required = false) String type) {
        List<Route> routes = routeService.getRoutesByFilters(city, type);
        return ResponseEntity.ok(routes);
    }

    @PostMapping
    public ResponseEntity<Route> createRoute(
            @Valid @RequestBody RouteCreateDTO dto) {
        Route route = routeService.createRoute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(route);
    }
}