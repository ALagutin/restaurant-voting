package ru.stencom.restaurantvoting.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.stencom.restaurantvoting.model.MenuItem;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<MenuItem>{
}
