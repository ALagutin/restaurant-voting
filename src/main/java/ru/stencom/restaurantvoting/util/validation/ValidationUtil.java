package ru.stencom.restaurantvoting.util.validation;

import lombok.experimental.UtilityClass;
import ru.stencom.restaurantvoting.error.IllegalRequestDataException;

@UtilityClass
public class ValidationUtil {

    public static void checkModification(int count, int id) {
        if (count == 0) {
            throw new IllegalRequestDataException("Entity with id=" + id + " not found");
        }
    }
}