package ru.stencom.restaurantvoting.util.to;

import ru.stencom.restaurantvoting.model.Vote;
import ru.stencom.restaurantvoting.to.VoteTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class VoteUtil {

    public static List<VoteTo> getTos(Collection<Vote> votes) {
        return votes.stream()
                .map(VoteUtil::createTo)
                .collect(Collectors.toList());
    }

    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.id(), vote.getDateOfMenu(), vote.getRestaurant().id());
    }
}
