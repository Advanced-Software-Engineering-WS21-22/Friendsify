package aau.at.friendsifybirthdayservice.controller;

import aau.at.friendsifybirthdayservice.exception.NoBirthdayException;
import aau.at.friendsifybirthdayservice.exception.ResourceNotFoundException;
import aau.at.friendsifybirthdayservice.model.Person;
import aau.at.friendsifybirthdayservice.repository.PersonDao;
import aau.at.friendsifybirthdayservice.service.EmailClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/birthday")
@Slf4j
public class BirthdayController {

    @Autowired
    private EmailClient client;

    @Autowired
    private PersonDao personDao;

    @GetMapping()
    public List<Person> listBirthdays() {
        LocalDate today = LocalDate.now();
        return personDao.findByBirthday(today);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Void> happyBirthday(@PathVariable("personId") Long personId) throws Exception {
        Person birthdayKid = personDao
                .findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + personId));
        if (!LocalDate.now().equals(birthdayKid.getBirthday())) {
            throw new NoBirthdayException();
        }

        ResponseEntity<Void> resp = client.send(birthdayKid.getEmail(), "Happy Birthday!");
        if (resp.getStatusCode() != HttpStatus.OK){
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.noContent().build();
    }

}
