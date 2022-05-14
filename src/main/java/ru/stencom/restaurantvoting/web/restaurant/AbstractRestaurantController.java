package ru.stencom.restaurantvoting.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import ru.stencom.restaurantvoting.error.IllegalRequestDataException;
import ru.stencom.restaurantvoting.model.Restaurant;
import ru.stencom.restaurantvoting.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
public class AbstractRestaurantController {

    @Autowired
    protected RestaurantRepository repository;

    public Restaurant get(@PathVariable int id) {
        Optional<Restaurant> restaurant = repository.get(id);
        if (restaurant.isPresent()) {
            return restaurant.get();
        } else {
            throw new IllegalRequestDataException("Restaurant with id=" + id + " not exist.");
        }
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public Restaurant getWithMenu(@PathVariable int id) {
        Optional<Restaurant> restaurant = repository.getWithMenu(id);
        if (restaurant.isPresent()) {
            return restaurant.get();
        } else {
            throw new IllegalRequestDataException("Restaurant with id=" + id + " not exist.");
        }
    }

    public List<Restaurant> getAllWithMenu() {
        return repository.getAllWithMenu();
    }
}
