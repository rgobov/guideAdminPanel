package gobov.roma.adminpanel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyInfoDTO {
    private Long id;
    private String city;
    private String emergencyPhone;
    private String hospitalAddress;
    private String safetyTips;
    private String museumContact;
}