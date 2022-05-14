package ru.stencom.restaurantvoting.web.restaraunt.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.stencom.restaurantvoting.model.Restaurant;
import ru.stencom.restaurantvoting.util.validation.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminRestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/api/admin/restaurants";

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        repository.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        log.info("create {}", restaurant);
        ValidationUtil.checkNew(restaurant);
        restaurant.setMenu(new ArrayList<>());
        Restaurant created = repository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update {} with id={}", restaurant, id);
        ValidationUtil.assureIdConsistent(restaurant, id);
        Restaurant updated = super.get(restaurant.id());
        updated.setName(restaurant.getName());
        updated.setAddress(restaurant.getAddress());
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }
}
