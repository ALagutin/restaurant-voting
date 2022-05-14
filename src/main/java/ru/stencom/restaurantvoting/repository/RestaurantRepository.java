package ru.stencom.restaurantvoting.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.stencom.restaurantvoting.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    default Optional<Restaurant> get(int id) {
        Optional<Restaurant> restaurant = findById(id);

        if (restaurant.isPresent()) {
            Restaurant restaurantWithoutMenu = restaurant.get();
            restaurantWithoutMenu.setMenu(new ArrayList<>());
            return Optional.of(restaurantWithoutMenu);
        } else {
            return restaurant;
        }
    }

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.menu as m WHERE r.id = :id AND m.dateMenu = CURRENT_DATE")
    Optional<Restaurant> getWithMenu(int id);

    default List<Restaurant> getAll() {
        List<Restaurant> restaurants = findAll();
        restaurants.forEach(restaurant -> restaurant.setMenu(new ArrayList<>()));
        return restaurants;
    }

    @EntityGraph(attributePaths = {"menu"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r JOIN FETCH r.menu as m WHERE m.dateMenu = CURRENT_DATE")
    List<Restaurant> getAllWithMenu();
}
