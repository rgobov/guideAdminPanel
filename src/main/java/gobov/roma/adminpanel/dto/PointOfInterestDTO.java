package gobov.roma.adminpanel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PointOfInterestDTO {

    @NotBlank(message = "Название обязательно")
    private String title;

    private String description;

    @NotNull(message = "Координаты обязательны")
    private LocationDTO location; // Вложенный DTO для координат

    @NotBlank(message = "Регион обязателен")
    private String region;

    private String audioUrl;

    private String imageUrl;

    private Integer order;

    private String beaconId;

    private Map<String, Object> arData;

    private Map<String, Object> indoorCoordinates;

    private Double arPrecision;

    // Вложенный DTO для представления координат
    @Getter
    @Setter
    public static class LocationDTO {
        @NotBlank(message = "Тип геометрии обязателен")
        private String type; // Должно быть "Point"

        @NotNull(message = "Координаты обязательны")
        private double[] coordinates; // [longitude, latitude]
    }
}