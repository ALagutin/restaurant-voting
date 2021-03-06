package ru.stencom.restaurantvoting.web.restaurant;


import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.stencom.restaurantvoting.model.Restaurant;
import ru.stencom.restaurantvoting.to.RestaurantTo;

import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@CacheConfig(cacheNames ="restaurants")
public class UserRestaurantController extends AbstractRestaurantController{

    static final String REST_URL = "/api/restaurants";

    @GetMapping("/with-menu")
    public List<Restaurant> getAllWithMenu() {
        return super.getAllWithMenu();
    }

    @GetMapping("/{id}/with-menu")
    public Restaurant getWithMenu(@PathVariable int id) {
        return super.getWithMenu(id);
    }

    @GetMapping
    @Cacheable
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}")
    @Cacheable
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping("/by-rating")
    public List<RestaurantTo> getMostPopularRestaurant() {
        return repository.getAllWithRating();
    }

}
