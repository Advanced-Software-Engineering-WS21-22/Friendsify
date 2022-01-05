package aau.at.friendsifyjokeservice.controller;

import aau.at.friendsifyjokeservice.exception.PersonNotFoundException;
import aau.at.friendsifyjokeservice.model.Person;
import aau.at.friendsifyjokeservice.repository.PersonDao;
import aau.at.friendsifyjokeservice.services.EmailClient;
import aau.at.friendsifyjokeservice.services.JokeService;
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
    private PersonDao personDao;

    @Autowired
    private EmailClient client;

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
        Person friend = personDao.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));
        // call joke service
        String joke = service.getJokebyType(type);
        // call Email Service
        client.send(friend.getEmail(), joke);

        return ResponseEntity.ok().build();

    }

}