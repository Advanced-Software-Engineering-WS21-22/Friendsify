package aau.at.friendsifypersonservice.servicelogics;

import aau.at.friendsifypersonservice.exceptions.PersonNotFoundException;
import aau.at.friendsifypersonservice.models.Person;
import aau.at.friendsifypersonservice.repositories.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonServiceLogic {

    @Autowired
    private PersonDao personDao;

    public List<Person> getAllPersons(){
        return personDao.findAll();}


    public Person getPersonByID(Long id) throws PersonNotFoundException {

        Person p = personDao.findById(id)
                .orElseThrow(()-> new PersonNotFoundException("Person not found by id: "+id));
        return p;
    }


    public Person getPersonByEmail(String email) throws PersonNotFoundException {

        Person p = personDao.findByEmail(email);
        if (p == null){
            throw new PersonNotFoundException("Person not found by email: "+email);
        }
        return p;
    }


    public Person createPerson(Person person){
        return personDao.save(person);
    }


    public Person updatePerson(Long id, Person newPerson) throws PersonNotFoundException {
        Person storedPerson = getPersonByID(id);

        storedPerson.update(newPerson);

        Person updatedPerson= personDao.save(storedPerson);
        return updatedPerson;
    }


    public Person updatePerson(String email, Person newPerson) throws PersonNotFoundException {
        Person storedPerson = getPersonByEmail(email);

        storedPerson.update(newPerson);
        Person updatedPerson= personDao.save(storedPerson);
        return updatedPerson;
    }

    public Map<String, Boolean> deletePerson(Long id)
            throws PersonNotFoundException {

        Person storedPerson = getPersonByID(id);

        personDao.delete(storedPerson);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    public Map<String, Boolean> deletePerson(String email) throws PersonNotFoundException {
        Person storedPerson = getPersonByEmail(email);

        personDao.delete(storedPerson);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
