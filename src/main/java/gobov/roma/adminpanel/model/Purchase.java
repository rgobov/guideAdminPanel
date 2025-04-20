package gobov.roma.adminpanel.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String itemType; // Тип покупки (например, "tour", "ticket")

    @Column(nullable = false)
    private Long itemId; // ID объекта покупки (например, ID тура)

    @Column
    private String itemName; // Название объекта покупки (например, название тура)

    @ElementCollection
    @CollectionTable(name = "purchase_environment_types", joinColumns = @JoinColumn(name = "purchase_id"))
    @Column(name = "environment_type")
    @Enumerated(EnumType.STRING)
    private Set<Route.EnvironmentType> environmentTypes; // Тип среды тура на момент покупки

    @Enumerated(EnumType.STRING)
    @Column
    private Route.TourFormat tourFormat; // Формат тура на момент покупки

    @Column(nullable = false)
    private LocalDateTime purchaseDate;

    @Column(nullable = false)
    private Double amount;

    @Column
    private String status; // Статус покупки (например, "pending", "completed")
}