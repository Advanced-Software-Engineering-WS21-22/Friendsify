package aau.at.friendsifyfrontendservice.services;

import aau.at.friendsifyfrontendservice.models.Friends;
import aau.at.friendsifyfrontendservice.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FindFriendsService {

    @Autowired
    private PersonService personService;

    @Autowired
    private FriendsService friendsService;

    private Person[] allPersons;

    private Friends[] alreadyFriends;

    public FindFriendsService() {
    }

    public void loadData(String email) {
        this.allPersons = personService.getPersons();
        //this.alreadyFriends = this.friendsService.getFriendsByInitiator(email);
    }

    public Person[] findSelectablePersons(String email_initiator) {

        /*
        ArrayList<Person> all = new ArrayList<Person>(Arrays.asList(this.allPersons));

        for (Person p: all) {
            for(Friends f : this.alreadyFriends) {
                if(p.getEmail() == f.getEmail_p_friend()) {
                    all.remove(p);
                }
            }
        } */

        return this.allPersons;
    }
}
