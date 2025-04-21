package gobov.roma.adminpanel.repository.emergency;

import gobov.roma.adminpanel.model.EmergencyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmergencyInfoRepository extends JpaRepository<EmergencyInfo, Long> {
    Optional<EmergencyInfo> findByCity(String city);
}