package ru.stencom.restaurantvoting.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.stencom.restaurantvoting.model.MenuItem;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<MenuItem> {

    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.id =:restaurantId AND m.dateMenu =:dateMenu  ORDER BY m.id")
    List<MenuItem> getAll(LocalDate dateMenu, int restaurantId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MenuItem m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM MenuItem m JOIN FETCH m.restaurant as r WHERE m.id =:id AND r.id =:restaurantId  ORDER BY m.id")
    MenuItem get(int id, int restaurantId);
}
