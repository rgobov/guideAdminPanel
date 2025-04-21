package gobov.roma.adminpanel.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FavoriteRequestDTO {
    @NotNull
    private Long userId;

    private Long routeId;
    private Long poiId;
}
