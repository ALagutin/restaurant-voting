package ru.stencom.restaurantvoting.web.vote;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.stencom.restaurantvoting.error.IllegalRequestDataException;
import ru.stencom.restaurantvoting.model.Vote;
import ru.stencom.restaurantvoting.to.VoteTo;
import ru.stencom.restaurantvoting.repository.VoteRepository;
import ru.stencom.restaurantvoting.service.VoteService;
import ru.stencom.restaurantvoting.util.to.VoteUtil;
import ru.stencom.restaurantvoting.web.AuthUser;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class UserVoteController {

    static final String REST_URL = "/api/profile/votes";

    static final int USER_ID = 1;

    protected VoteRepository repository;
    protected VoteService service;


    @PostMapping
    public ResponseEntity<VoteTo> createWithLocation(@AuthenticationPrincipal AuthUser authUser, @RequestParam int restaurantId) {
        int userId = authUser.id();
        log.info("create vote for restaurant id={} user id={}", userId, restaurantId);
        VoteTo created = service.save(userId, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping
    public VoteTo get(@AuthenticationPrincipal AuthUser authUser) {
        int userId = authUser.id();
        Optional<Vote> vote = repository.get(userId);

        if (vote.isPresent()) {
            return VoteUtil.createTo(vote.get());
        } else {
            throw new IllegalRequestDataException("today's vote is not exist.");
        }
    }

    @GetMapping("/all")
    public List<VoteTo> getAll(@AuthenticationPrincipal AuthUser authUser) {
        int userId = authUser.id();
        return VoteUtil.getTos(repository.getAll(userId));
    }

}
