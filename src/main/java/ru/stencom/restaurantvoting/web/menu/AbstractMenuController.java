package ru.stencom.restaurantvoting.web.menu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import ru.stencom.restaurantvoting.model.MenuItem;
import ru.stencom.restaurantvoting.repository.MenuRepository;

import java.util.List;

@Slf4j
public abstract class AbstractMenuController {

    @Autowired
    protected MenuRepository repository;

    public List<MenuItem> getAll(@PathVariable int restaurantId) {
        return repository.getAll(restaurantId);
    }

}
