package ru.stencom.restaurantvoting.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.stencom.restaurantvoting.model.Vote;

@Transactional
public interface VoteRepository extends BaseRepository<Vote> {
}
