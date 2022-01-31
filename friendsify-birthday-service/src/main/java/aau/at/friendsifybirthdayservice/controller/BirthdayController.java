package aau.at.friendsifybirthdayservice.controller;

import aau.at.friendsifybirthdayservice.exception.NoBirthdayException;
import aau.at.friendsifybirthdayservice.exception.ResourceNotFoundException;
import aau.at.friendsifybirthdayservice.obj.Person;
import aau.at.friendsifybirthdayservice.service.BirthdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/birthday")
public class BirthdayController {

    @Autowired
    private BirthdayService birthdayService;

    @GetMapping()
    public List<Person> listBirthdays() {
        return birthdayService.listBirthdayKids();
    }

    @GetMapping("/{personId}")
    public List<Person> listBirthdayOfFriends(@PathVariable("personId") Long personId) {
        return birthdayService.listBirthdayOfFriends(personId);
    }

    @PutMapping("/{personId}/{birthdayKidId}")
    public ResponseEntity<Void> happyBirthday(@PathVariable("personId") Long personId, @PathVariable("personId") Long birthdayKidId) throws NoBirthdayException, ResourceNotFoundException {
        birthdayService.happyBirthday(personId, birthdayKidId);

        return ResponseEntity.ok().build();
    }

}
