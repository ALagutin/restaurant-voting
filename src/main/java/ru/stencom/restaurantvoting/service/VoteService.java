package ru.stencom.restaurantvoting.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stencom.restaurantvoting.error.IllegalRequestDataException;
import ru.stencom.restaurantvoting.model.Restaurant;
import ru.stencom.restaurantvoting.model.Vote;
import ru.stencom.restaurantvoting.repository.RestaurantRepository;
import ru.stencom.restaurantvoting.repository.UserRepository;
import ru.stencom.restaurantvoting.repository.VoteRepository;
import ru.stencom.restaurantvoting.to.VoteTo;
import ru.stencom.restaurantvoting.util.to.VoteUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class VoteService {

    private static final LocalTime TIME_TO_CHANGE_VOTE = LocalTime.of(11, 0);

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Transactional
    public VoteTo save(int userId, int restaurantId) throws IllegalRequestDataException{
        Optional<Vote> vote = voteRepository.get(userId);

        if (vote.isPresent()) {
            if (isTimeToChangeVote()) {
                Restaurant restaurant = restaurantRepository.getById(restaurantId);
                vote.get().setRestaurant(restaurant);
                return VoteUtil.createTo(vote.get());
            } else {
                throw new IllegalRequestDataException("To late for voting, comeback tomorrow");
            }
        }

        log.info("vote for restaurant with id={} from user with id={}", restaurantId, userId);
        Vote newVote = new Vote(LocalDate.now(), userRepository.getById(userId), restaurantRepository.getById(restaurantId));
        Vote saved = voteRepository.save(newVote);
        return VoteUtil.createTo(voteRepository.save(newVote));
    }

    private boolean isTimeToChangeVote() {
        return LocalTime.now().isBefore(TIME_TO_CHANGE_VOTE);
    }
}
