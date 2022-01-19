package aau.at.friendsifyjokeservice.controller;

import aau.at.friendsifyjokeservice.services.EmailClient;
import aau.at.friendsifyjokeservice.services.JokeService;
import aau.at.friendsifyjokeservice.services.PersonClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/jokes")
public class JokeController {

    @Autowired
    private JokeService service;

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private PersonClient personClient;

    @GetMapping(value = "/")
    public ResponseEntity<String> getJoke() {
        return getJoke(null);
    }
    @GetMapping(value = "/{type}")
    public ResponseEntity<String> getJoke(@PathVariable(name = "type", required = false) String type) {
        String joke = service.getJokebyType(type);
        if (StringUtils.isBlank(joke)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(joke);
    }

    @PostMapping(value = { "/{personId}"})
    public ResponseEntity<String> tellYourFriendAJoke(
            @PathVariable("personId") Long personId
    ) throws Exception {
        return tellYourFriendAJoke(personId, null);
    }

    @PostMapping(value = "/{personId}/{type}")
    public ResponseEntity<String> tellYourFriendAJoke(
            @PathVariable("personId") Long personId,
            @PathVariable(name = "type", required = false) String type
    ) throws Exception {
        // call Person service
        String email = personClient.emailOfPerson(personId);
        // call joke service
        String joke = service.getJokebyType(type);
        // call Email Service
        emailClient.send(email, joke);

        return ResponseEntity.ok().build();
    }
}