package aau.at.friendsifypersonservice.controllers;

import aau.at.friendsifypersonservice.exceptions.PersonNotFoundException;
import aau.at.friendsifypersonservice.models.Person;
import aau.at.friendsifypersonservice.repositories.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @GetMapping()
    public List<Person> getAllPersons(){
        System.out.println("Hey!");
        return personDao.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonByID(@PathVariable("id")Long id) throws PersonNotFoundException {

        Person p = personDao.findById(id)
                .orElseThrow(()-> new PersonNotFoundException("Person not found by id: "+id));
        return ResponseEntity.ok(p);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Person> getPersonByEmail(@PathVariable("email")String email) throws PersonNotFoundException {

        Person p = personDao.findByEmail(email);
        if (p == null){
            throw new PersonNotFoundException("Person not found by email: "+email);
        }
        return ResponseEntity.ok(p);
    }

    @PostMapping()
    public Person createPerson(@RequestBody Person person){
        return personDao.save(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Long id, @RequestBody Person newPerson) throws PersonNotFoundException {
        Person storedPerson = personDao.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found by id: "+id));

        storedPerson.update(newPerson);

        Person updatedPerson= personDao.save(storedPerson);
        return ResponseEntity.ok(updatedPerson);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Person> updatePerson(@PathVariable("email") String email, @RequestBody Person newPerson) throws PersonNotFoundException {
        Person storedPerson = personDao.findByEmail(email);
        if (storedPerson==null) throw new PersonNotFoundException("Person not found by email: "+email);

        storedPerson.update(newPerson);
        Person updatedPerson= personDao.save(storedPerson);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deletePerson(@PathVariable("id") Long id)
            throws PersonNotFoundException {

        Person storedPerson = personDao.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found by id: "+id));

        personDao.delete(storedPerson);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/{email}")
    public Map<String, Boolean> deletePerson(@PathVariable("email") String email)
            throws PersonNotFoundException {

        Person storedPerson = personDao.findByEmail(email);
        if (storedPerson==null) throw new PersonNotFoundException("Person not found by email: "+email);

        personDao.delete(storedPerson);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
