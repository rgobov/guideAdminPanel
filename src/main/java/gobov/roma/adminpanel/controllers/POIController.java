package gobov.roma.adminpanel.controllers;

import gobov.roma.adminpanel.dto.PointOfInterestDTO;
import gobov.roma.adminpanel.model.PointOfInterest;
import gobov.roma.adminpanel.services.PointOfInterestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poi")
@RequiredArgsConstructor
public class POIController {
    private final PointOfInterestService poiService;
    private final GeometryFactory geometryFactory = new GeometryFactory(); // Для создания объекта Point

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
    public ResponseEntity<PointOfInterest> createPOI(@Valid @RequestBody PointOfInterestDTO poiDTO) {
        // Преобразование DTO в сущность
        PointOfInterest poi = new PointOfInterest();
        poi.setTitle(poiDTO.getTitle());
        poi.setDescription(poiDTO.getDescription());
        poi.setRegion(poiDTO.getRegion());
        poi.setAudioUrl(poiDTO.getAudioUrl());
        poi.setImageUrl(poiDTO.getImageUrl());
        poi.setOrder(poiDTO.getOrder());
        poi.setBeaconId(poiDTO.getBeaconId());
        poi.setArData(poiDTO.getArData());
        poi.setIndoorCoordinates(poiDTO.getIndoorCoordinates());
        poi.setArPrecision(poiDTO.getArPrecision());

        // Преобразование LocationDTO в Point
        PointOfInterestDTO.LocationDTO locationDTO = poiDTO.getLocation();
        if (!"Point".equals(locationDTO.getType()) || locationDTO.getCoordinates().length != 2) {
            throw new IllegalArgumentException("Неверный формат координат. Ожидается тип 'Point' и массив [longitude, latitude].");
        }
        double longitude = locationDTO.getCoordinates()[0];
        double latitude = locationDTO.getCoordinates()[1];
        Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        point.setSRID(4326); // Устанавливаем SRID для PostGIS
        poi.setLocation(point);

        // Сохранение сущности
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