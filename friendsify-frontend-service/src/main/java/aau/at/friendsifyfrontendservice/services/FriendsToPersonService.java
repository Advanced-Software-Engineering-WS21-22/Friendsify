package aau.at.friendsifyfrontendservice.services;

import aau.at.friendsifyfrontendservice.dataSamples.PersonRepository;
import aau.at.friendsifyfrontendservice.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FriendsToPersonService {

    @Autowired
    PersonRepository personRepository;

    public Person getPerson(Long id_p) {
        Person found = null;
        ArrayList<Person> allPersons = this.personRepository.getPersons();
        for (Person p: allPersons) {
            if(p.getId_p() == id_p) {
                found = p;
            }
        }
        return found;
    }

}
