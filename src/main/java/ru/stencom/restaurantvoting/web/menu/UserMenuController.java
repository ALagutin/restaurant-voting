package ru.stencom.restaurantvoting.web.menu;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stencom.restaurantvoting.model.MenuItem;

import java.util.List;

@RestController
@RequestMapping(value = UserMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@CacheConfig(cacheNames = "menus")
public class UserMenuController extends AbstractMenuController{

    static final String REST_URL = "/api/restaurants/{id}/menus";

    @GetMapping
    @Cacheable
    public List<MenuItem> getAll(@PathVariable int id) {
        return super.getAll(id);
    }
}
