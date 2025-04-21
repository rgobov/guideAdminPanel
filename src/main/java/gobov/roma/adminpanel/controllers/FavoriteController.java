package gobov.roma.adminpanel.controllers;

import gobov.roma.adminpanel.dto.FavoriteRequestDTO;
import gobov.roma.adminpanel.model.Favorite;
import gobov.roma.adminpanel.services.FavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<Favorite> addFavorite(
            @Valid @RequestBody FavoriteRequestDTO request) {
        Favorite favorite = favoriteService.addFavorite(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(favorite);
    }
}