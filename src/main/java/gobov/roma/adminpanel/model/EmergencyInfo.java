package gobov.roma.adminpanel.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "emergency_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmergencyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(name = "emergency_phone")
    private String emergencyPhone;

    @Column(name = "hospital_address")
    private String hospitalAddress;

    @Column(name = "safety_tips")
    private String safetyTips;

    @Column(name = "museum_contact")
    private String museumContact;
}