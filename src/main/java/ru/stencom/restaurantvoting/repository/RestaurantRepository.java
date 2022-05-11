package ru.stencom.restaurantvoting.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.stencom.restaurantvoting.model.Restaurant;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
}
