package aau.at.friendsifyfrontendservice.services;

import aau.at.friendsifyfrontendservice.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class FriendsToPersonService {

    @Autowired
    private PersonService personService;

    private Person[] allPersons;

    public FriendsToPersonService() {
    }

    public void loadPersons() {
        this.allPersons = personService.getPersons();
    }

    public Person getPerson(String email) {
        Person found = null;
        for (Person p: allPersons) {
            if(p.getEmail().equals(email)) {
                found = p;
            }
        }
        return found;
    }

}
