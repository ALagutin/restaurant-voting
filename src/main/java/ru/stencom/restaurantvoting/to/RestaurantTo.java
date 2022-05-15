package ru.stencom.restaurantvoting.to;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantTo {
    private Integer id;
    private String name;
    private long rating;
}
