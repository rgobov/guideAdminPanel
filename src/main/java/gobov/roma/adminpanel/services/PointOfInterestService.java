package gobov.roma.adminpanel.services;

import gobov.roma.adminpanel.model.PointOfInterest;
import gobov.roma.adminpanel.repository.point.PointOfInterestRepository;
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
        return poiRepository.findNearbyPoints(lat, lng, radius);
    }

    public PointOfInterest createPoint(PointOfInterest poi) {
        return poiRepository.save(poi);
    }

    public List<PointOfInterest> getPointsByRegion(String region) {
        return poiRepository.findByRegion(region);
    }

    public List<PointOfInterest> getPointsWithinBoundingBox(double minLng, double minLat, double maxLng, double maxLat) {
        return poiRepository.findWithinBoundingBox(minLng, minLat, maxLng, maxLat);
    }
}