package ru.stencom.restaurantvoting.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.stencom.restaurantvoting.model.User;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User>{
}
