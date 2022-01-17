package controllers;

import exceptions.PersonNotFoundException;
import models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.PersonDao;

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
        System.out.println("Get all persons");
        return personDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonByID(@PathVariable("id")Long id) throws PersonNotFoundException {

        Person p = personDao.findById(id)
                .orElseThrow(()-> new PersonNotFoundException("Person not found by id: "+id));
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
}
