package gobov.roma.adminpanel.services;

import gobov.roma.adminpanel.dto.FavoriteRequestDTO;
import gobov.roma.adminpanel.model.Favorite;
import gobov.roma.adminpanel.model.PointOfInterest;
import gobov.roma.adminpanel.model.Route;
import gobov.roma.adminpanel.model.User;
import gobov.roma.adminpanel.repository.favorite.FavoriteRepository;
import gobov.roma.adminpanel.repository.point.PointOfInterestRepository;
import gobov.roma.adminpanel.repository.excursion.RouteRepository;
import gobov.roma.adminpanel.repository.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;
    private final PointOfInterestRepository pointOfInterestRepository;

    @Transactional
    public Favorite addFavorite(FavoriteRequestDTO request) {
        // Проверяем, что указан хотя бы один объект для добавления в избранное
        if (request.getRouteId() == null && request.getPoiId() == null) {
            throw new IllegalArgumentException("Either routeId or poiId must be provided");
        }

        // Получаем пользователя
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Создаем новую запись избранного
        Favorite favorite = Favorite.builder()
                .user(user)
                .addedAt(LocalDateTime.now())
                .build();

        // Устанавливаем маршрут или точку интереса
        if (request.getRouteId() != null) {
            Route route = routeRepository.findById(request.getRouteId())
                    .orElseThrow(() -> new IllegalArgumentException("Route not found"));
            favorite.setRoute(route);
        } else {
            PointOfInterest poi = pointOfInterestRepository.findById(request.getPoiId())
                    .orElseThrow(() -> new IllegalArgumentException("Point of interest not found"));
            favorite.setPointOfInterest(poi);
        }

        // Проверяем, не существует ли уже такая запись
        if (request.getRouteId() != null && favoriteRepository.existsByUserAndRoute(user, favorite.getRoute())) {
            throw new IllegalArgumentException("This route is already in favorites");
        }

        if (request.getPoiId() != null && favoriteRepository.existsByUserAndPointOfInterest(user, favorite.getPointOfInterest())) {
            throw new IllegalArgumentException("This point of interest is already in favorites");
        }

        return favoriteRepository.save(favorite);
    }
}