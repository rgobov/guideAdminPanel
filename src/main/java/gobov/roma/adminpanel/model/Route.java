package gobov.roma.adminpanel.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "routes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private String city;

    @Column
    private Integer duration; // В минутах

    @Column
    private Double distance; // В километрах

    @ElementCollection
    @CollectionTable(name = "route_categories", joinColumns = @JoinColumn(name = "route_id"))
    @Column(name = "category")
    private List<String> categories;

    @ElementCollection
    @CollectionTable(name = "route_environment_types", joinColumns = @JoinColumn(name = "route_id"))
    @Column(name = "environment_type")
    @Enumerated(EnumType.STRING)
    private Set<EnvironmentType> environmentTypes; // Может быть OUTDOOR, INDOOR или их комбинация

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TourFormat tourFormat; // Формат тура: SELF_GUIDED или GUIDED

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "route_points",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "point_id")
    )
    private List<PointOfInterest> points;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public enum EnvironmentType {
        OUTDOOR, INDOOR
    }

    public enum TourFormat {
        SELF_GUIDED, GUIDED
    }
}