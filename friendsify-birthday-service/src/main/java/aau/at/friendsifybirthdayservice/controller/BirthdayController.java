package aau.at.friendsifybirthdayservice.controller;

import aau.at.friendsifybirthdayservice.exception.NoBirthdayException;
import aau.at.friendsifybirthdayservice.exception.ResourceNotFoundException;
import aau.at.friendsifybirthdayservice.obj.Person;
import aau.at.friendsifybirthdayservice.service.EmailClient;
import aau.at.friendsifybirthdayservice.service.PersonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/birthday")
public class BirthdayController {

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private PersonClient personClient;

    @GetMapping()
    public List<Person> listBirthdays() {
        return personClient.findByBirthday(LocalDate.now());
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Void> happyBirthday(@PathVariable("personId") Long personId) throws Exception {
        Person birthdayKid = personClient.getPersonById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find person"));
        if (!LocalDate.now().equals(birthdayKid.getBirthday())) {
            throw new NoBirthdayException();
        }

        ResponseEntity<Void> resp = emailClient.sendBirthdayWish(birthdayKid.getEmail());
        if (resp.getStatusCode() != HttpStatus.OK){
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.noContent().build();
    }

}
