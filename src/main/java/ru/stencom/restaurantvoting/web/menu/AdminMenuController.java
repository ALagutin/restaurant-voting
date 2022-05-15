package ru.stencom.restaurantvoting.web.menu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.stencom.restaurantvoting.error.IllegalRequestDataException;
import ru.stencom.restaurantvoting.model.MenuItem;
import ru.stencom.restaurantvoting.util.validation.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "menus")
public class AdminMenuController extends AbstractMenuController {

    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/menus";

    @GetMapping
    public List<MenuItem> getAll(@PathVariable int restaurantId) {
        return super.getAll(restaurantId);
    }

    @GetMapping("/{menuId}")
    public MenuItem get(@PathVariable int menuId, @PathVariable int restaurantId) {
        return menuRepository.get(menuId, restaurantId);
    }

    @GetMapping("/by-date")
    public List<MenuItem> getAllByDate(@PathVariable int restaurantId, @RequestParam("dateMenu")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateMenu) {
        return menuRepository.getAll(dateMenu, restaurantId);
    }

    @DeleteMapping("/{menuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void delete(@PathVariable int menuId, @PathVariable int restaurantId) {
        menuRepository.delete(menuId, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    @CacheEvict(allEntries = true)
    public ResponseEntity<MenuItem> createWithLocation(@Valid @RequestBody MenuItem menuItem, @PathVariable int restaurantId) {
        log.info("create {}", menuItem);
        ValidationUtil.checkNew(menuItem);
        menuItem.setRestaurant(restaurantRepository.get(restaurantId).orElseThrow(() -> new IllegalRequestDataException("Restaurant with id=" + restaurantId + " not exist.")));
        MenuItem created = menuRepository.save(menuItem);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/" + created.getId())
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(allEntries = true)
    public void update(@Valid @RequestBody MenuItem menuItem, @PathVariable int menuId, @PathVariable int restaurantId) {
        log.info("update {} for restaurant {}", menuItem, menuId);
        MenuItem updated = menuRepository.get(menuId, restaurantId);
        if (updated == null) {
            throw new IllegalRequestDataException("Menu item with id=" + menuId + "and restaurant with id=" + restaurantId + " not exist.");
        }
        ValidationUtil.assureIdConsistent(menuItem.getRestaurant(), restaurantId);
        ValidationUtil.assureIdConsistent(menuItem, menuId);
        updated.setName(menuItem.getName());
        updated.setDateMenu(menuItem.getDateMenu());
        updated.setPriceCents(menuItem.getPriceCents());
    }
}
