package ru.stencom.restaurantvoting.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.stencom.restaurantvoting.model.MenuThing;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<MenuThing>{
}
