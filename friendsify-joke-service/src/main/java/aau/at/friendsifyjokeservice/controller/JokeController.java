package aau.at.friendsifyjokeservice.controller;

import aau.at.friendsifyjokeservice.services.EmailClient;
import aau.at.friendsifyjokeservice.services.JokeService;
import aau.at.friendsifyjokeservice.services.PersonClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/jokes")
@Slf4j
public class JokeController {

    @Autowired
    private JokeService service;

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private PersonClient personClient;

    @GetMapping("/?type={type}")
    public ResponseEntity<String> getJoke(@PathVariable(name = "type", required = false) String type) {
        String joke = service.getJokebyType(type);
        log.debug(joke);
        if (StringUtils.isBlank(joke)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(joke);
    }

    @PostMapping("/{personId}?type={type}")
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