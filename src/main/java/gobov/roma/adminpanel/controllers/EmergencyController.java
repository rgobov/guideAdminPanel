package gobov.roma.adminpanel.controllers;

import gobov.roma.mvpguide.dto.EmergencyInfoDTO;
import gobov.roma.mvpguide.services.EmergencyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emergency")
public class EmergencyController {

    private final EmergencyInfoService emergencyService;

    @Autowired
    public EmergencyController(EmergencyInfoService emergencyService) {
        this.emergencyService = emergencyService;
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<EmergencyInfoDTO> getCityInfo(@PathVariable String city) {
        EmergencyInfoDTO info = emergencyService.getEmergencyInfoByCity(city);
        if (info != null) {
            return ResponseEntity.ok(info);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}