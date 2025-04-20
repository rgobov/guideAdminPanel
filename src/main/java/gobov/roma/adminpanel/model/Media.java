package gobov.roma.adminpanel.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "poi_id", nullable = false)
    private PointOfInterest pointOfInterest;

    @Column(nullable = false)
    private String type; // "audio", "image", "3d_model", "ar_texture"

    @Column(nullable = false)
    private String url;

    @Column
    private Long size; // В байтах

    @Column
    private String language;

    @Column(name = "ar_content")
    private Boolean arContent; // Является ли файл частью AR
}