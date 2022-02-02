package aau.at.friendsifypersonservice.controllers;

import aau.at.friendsifypersonservice.exceptions.PersonNotFoundException;
import aau.at.friendsifypersonservice.models.Person;
import aau.at.friendsifypersonservice.servicelogics.PersonServiceLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonServiceLogic personService;

    @GetMapping()
    public List<Person> getAllPersons(){
        return personService.getAllPersons();}

    @GetMapping(params="id")
    public ResponseEntity<Person> getPersonByID(@RequestParam Long id) throws PersonNotFoundException {
        Person p = personService.getPersonByID(id);
        return ResponseEntity.ok(p);
    }

    @GetMapping(params = "email")
    public ResponseEntity<Person> getPersonByEmail(@RequestParam String email) throws PersonNotFoundException {
        Person p = personService.getPersonByEmail(email);
        return ResponseEntity.ok(p);
    }

    @PostMapping()
    public Person createPerson(@Valid @RequestBody Person person){
        return personService.createPerson(person);
    }

    @PutMapping(params = "id")
    public ResponseEntity<Person> updatePerson(@RequestParam Long id, @Valid @RequestBody Person newPerson) throws PersonNotFoundException {
        Person updatedPerson= personService.updatePerson(id,newPerson);
        return ResponseEntity.ok(updatedPerson);
    }

    @PutMapping(params = "email")
    public ResponseEntity<Person> updatePerson(@RequestParam String email, @Valid @RequestBody Person newPerson) throws PersonNotFoundException {
        Person updatedPerson= personService.updatePerson(email,newPerson);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Map<String, Boolean>> deletePerson(@RequestParam Long id)
            throws PersonNotFoundException {
        return ResponseEntity.ok(personService.deletePerson(id));
    }

    @DeleteMapping(params = "email")
    public ResponseEntity<Map<String, Boolean>> deletePerson(@RequestParam String email)
            throws PersonNotFoundException {
        return ResponseEntity.ok(personService.deletePerson(email));
    }
}
