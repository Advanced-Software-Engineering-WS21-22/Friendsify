package aau.at.friendsifyjokeservice.controller;

import aau.at.friendsifyjokeservice.exception.PersonNotFoundException;
import aau.at.friendsifyjokeservice.services.JokeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/jokes")
public class JokeController {

    @Autowired
    private JokeService jokeService;

    @GetMapping(value = "/")
    public ResponseEntity<String> getJoke() {
        return getJoke(null);
    }

    @GetMapping(value = "/{type}")
    public ResponseEntity<String> getJoke(@PathVariable(name = "type", required = false) String type) {
        String joke = jokeService.getJokebyType(type);
        if (StringUtils.isBlank(joke)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(joke);
    }

    @PutMapping(value = { "/{personId}/{friendId}"})
    public ResponseEntity<Void> tellYourFriendAJoke(
            @PathVariable("personId") Long personId,
            @PathVariable("friendId") Long friendId
    ) throws PersonNotFoundException {
        jokeService.tellYourFriendAJoke(personId, friendId);

        return ResponseEntity.noContent().build();
    }
}