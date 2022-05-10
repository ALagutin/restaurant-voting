package ru.stencom.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stencom.restaurantvoting.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}