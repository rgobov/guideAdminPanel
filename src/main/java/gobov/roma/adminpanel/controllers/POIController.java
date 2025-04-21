package gobov.roma.adminpanel.controllers;

import gobov.roma.adminpanel.model.PointOfInterest;
import gobov.roma.adminpanel.services.PointOfInterestService;
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

    @GetMapping
    public ResponseEntity<List<PointOfInterest>> getAllPOIs() {
        List<PointOfInterest> pois = poiService.getAllPoints();
        return ResponseEntity.ok(pois);
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<PointOfInterest>> getNearby(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam double radius) {
        List<PointOfInterest> pois = poiService.getNearbyPoints(lat, lng, radius);
        return ResponseEntity.ok(pois);
    }

    @PostMapping
    public ResponseEntity<PointOfInterest> createPOI(@Valid @RequestBody PointOfInterest poi) {
        PointOfInterest createdPOI = poiService.createPoint(poi);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPOI);
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<PointOfInterest>> getPointsByRegion(@PathVariable String region) {
        List<PointOfInterest> pois = poiService.getPointsByRegion(region);
        return ResponseEntity.ok(pois);
    }

    @GetMapping("/within")
    public ResponseEntity<List<PointOfInterest>> getPointsWithinBoundingBox(
            @RequestParam double minLng,
            @RequestParam double minLat,
            @RequestParam double maxLng,
            @RequestParam double maxLat) {
        List<PointOfInterest> pois = poiService.getPointsWithinBoundingBox(minLng, minLat, maxLng, maxLat);
        return ResponseEntity.ok(pois);
    }
}