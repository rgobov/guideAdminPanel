package gobov.roma.adminpanel.repository.favorite;

import gobov.roma.adminpanel.model.Favorite;
import gobov.roma.adminpanel.model.PointOfInterest;
import gobov.roma.adminpanel.model.Route;
import gobov.roma.adminpanel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    // Проверяет, есть ли у пользователя уже этот маршрут в избранном
    boolean existsByUserAndRoute(User user, Route route);

    // Проверяет, есть ли у пользователя уже эта точка интереса в избранном
    boolean existsByUserAndPointOfInterest(User user, PointOfInterest pointOfInterest);

    // Дополнительные полезные методы:

    // Найти все избранное пользователя
    List<Favorite> findByUser(User user);

    // Найти избранное по ID пользователя
    List<Favorite> findByUserId(Long userId);

    // Проверить существование по ID пользователя и ID маршрута
    boolean existsByUserIdAndRouteId(Long userId, Long routeId);

    // Проверить существование по ID пользователя и ID точки интереса
    boolean existsByUserIdAndPointOfInterestId(Long userId, Long poiId);
}