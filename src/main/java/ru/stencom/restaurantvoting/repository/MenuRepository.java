package ru.stencom.restaurantvoting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.stencom.restaurantvoting.model.MenuItem;

import java.util.List;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<MenuItem> {

    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.id = :restaurantId AND m.dateMenu = CURRENT_DATE")
    List<MenuItem> getAll(int restaurantId);
}
