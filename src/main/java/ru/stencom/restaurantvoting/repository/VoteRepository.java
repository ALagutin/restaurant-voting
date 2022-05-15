package ru.stencom.restaurantvoting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.stencom.restaurantvoting.model.Vote;

import java.util.Collection;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant as r WHERE v.user.id =:userId AND v.dateOfMenu = CURRENT_DATE ORDER BY v.dateOfMenu")
    Optional<Vote> get(int userId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant as r WHERE v.user.id =:userId ORDER BY v.dateOfMenu")
    Collection<Vote> getAll(int userId);

}
