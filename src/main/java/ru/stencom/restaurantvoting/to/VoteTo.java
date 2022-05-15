package ru.stencom.restaurantvoting.to;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class VoteTo {
    private int id;
    private LocalDate dateOfMenu;
    private int restaurantId;

    public int getId() {
        return id;
    }
}
