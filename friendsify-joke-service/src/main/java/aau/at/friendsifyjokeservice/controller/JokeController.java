package aau.at.friendsifyjokeservice.controller;

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

    @GetMapping("/")
    public ResponseEntity<String> jokeOfTheDay() {
        String joke = service.jokeOfTheDay();
        log.debug(joke);
        if (StringUtils.isBlank(joke)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(joke);
    }

    @PostMapping("/{personId}")
    public ResponseEntity<String> tellYourFriendAJoke(@PathVariable("personId") Long personId) {
        String joke = service.jokeOfTheDay();

        //TODO call Person service

        //TODO call Email Service

        return ResponseEntity.ok().build();

    }

}