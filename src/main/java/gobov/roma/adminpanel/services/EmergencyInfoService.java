package gobov.roma.adminpanel.services;

import gobov.roma.adminpanel.dto.EmergencyInfoDTO;
import gobov.roma.adminpanel.model.EmergencyInfo;
import gobov.roma.adminpanel.repository.emergency.EmergencyInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmergencyInfoService {
    private final EmergencyInfoRepository emergencyInfoRepository;

    public List<EmergencyInfo> getAllEmergencyInfo() {
        return emergencyInfoRepository.findAll();
    }

    public EmergencyInfo getEmergencyInfoById(Long id) {
        return emergencyInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emergency Info not found with id " + id));
    }

    public EmergencyInfo createEmergencyInfo(EmergencyInfo emergencyInfo) {
        if (emergencyInfo.getCity() == null || emergencyInfo.getCity().isEmpty()) {
            throw new IllegalArgumentException("City cannot be empty");
        }
        return emergencyInfoRepository.save(emergencyInfo);
    }

    public EmergencyInfo updateEmergencyInfo(Long id, EmergencyInfo updatedInfo) {
        if (updatedInfo.getCity() == null || updatedInfo.getCity().isEmpty()) {
            throw new IllegalArgumentException("City cannot be empty");
        }
        return emergencyInfoRepository.findById(id)
                .map(info -> {
                    info.setCity(updatedInfo.getCity());
                    info.setEmergencyPhone(updatedInfo.getEmergencyPhone());
                    info.setHospitalAddress(updatedInfo.getHospitalAddress());
                    info.setSafetyTips(updatedInfo.getSafetyTips());
                    info.setMuseumContact(updatedInfo.getMuseumContact());
                    return emergencyInfoRepository.save(info);
                })
                .orElseThrow(() -> new RuntimeException("Emergency Info not found with id " + id));
    }
    public EmergencyInfoDTO getEmergencyInfoByCity(String city) {
        EmergencyInfo info = emergencyInfoRepository.findByCity(city)
                .orElseThrow(() -> new RuntimeException("Emergency info not found for city: " + city));

        return EmergencyInfoDTO.builder()
                .id(info.getId())
                .city(info.getCity())
                .emergencyPhone(info.getEmergencyPhone())
                .hospitalAddress(info.getHospitalAddress())
                .safetyTips(info.getSafetyTips())
                .museumContact(info.getMuseumContact())
                .build();
    }

    public void deleteEmergencyInfo(Long id) {
        if (!emergencyInfoRepository.existsById(id)) {
            throw new RuntimeException("Emergency Info not found with id " + id);
        }
        emergencyInfoRepository.deleteById(id);
    }
}