package gobov.roma.adminpanel.service;


import gobov.roma.adminpanel.model.PointOfInterest;
import gobov.roma.adminpanel.repository.PointOfInterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointOfInterestService {
    private final PointOfInterestRepository poiRepository;

    public List<PointOfInterest> getAllPoints() {
        return poiRepository.findAll();
    }

    public List<PointOfInterest> getNearbyPoints(double lat, double lng, double radius) {
        // Логика для поиска ближайших точек (например, с использованием PostGIS)
        return poiRepository.findNearbyPoints(lat, lng, radius);
    }

    public PointOfInterest createPoint(PointOfInterest poi) {
        return poiRepository.save(poi);
    }
}