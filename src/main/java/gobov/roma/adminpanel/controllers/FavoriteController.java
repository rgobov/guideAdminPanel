package gobov.roma.adminpanel.controllers;

import gobov.roma.mvpguide.dto.FavoriteRequest;
import gobov.roma.mvpguide.model.Favorite;
import gobov.roma.mvpguide.services.FavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<Favorite> addFavorite(
            @Valid @RequestBody FavoriteRequest request) {
        Favorite favorite = favoriteService.addFavorite(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(favorite);
    }
}