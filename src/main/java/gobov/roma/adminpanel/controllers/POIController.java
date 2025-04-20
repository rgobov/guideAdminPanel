package gobov.roma.adminpanel.controllers;

import gobov.roma.mvpguide.model.PointOfInterest;
import gobov.roma.mvpguide.services.PointOfInterestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poi")
@RequiredArgsConstructor
public class POIController {
    private final PointOfInterestService poiService;

    // Получение всех точек интереса
    @GetMapping
    public ResponseEntity<List<PointOfInterest>> getAllPOIs() {
        List<PointOfInterest> pois = poiService.getAllPoints();
        return ResponseEntity.ok(pois);
    }

    // Получение точек интереса поблизости
    @GetMapping("/nearby")
    public ResponseEntity<List<PointOfInterest>> getNearby(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam double radius) {
        List<PointOfInterest> pois = poiService.getNearbyPoints(lat, lng, radius);
        return ResponseEntity.ok(pois);
    }

    // Создание новой точки интереса
    @PostMapping
    public ResponseEntity<PointOfInterest> createPOI(@Valid @RequestBody PointOfInterest poi) {
        PointOfInterest createdPOI = poiService.createPoint(poi);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPOI);
    }
}