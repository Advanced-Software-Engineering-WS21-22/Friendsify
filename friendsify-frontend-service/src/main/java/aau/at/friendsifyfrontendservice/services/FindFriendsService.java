package aau.at.friendsifyfrontendservice.services;

import aau.at.friendsifyfrontendservice.models.Friends;
import aau.at.friendsifyfrontendservice.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

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
        try {
            this.alreadyFriends = this.friendsService.getFriendsByInitiator(email);
        } catch (Exception e) {
            this.alreadyFriends = new Friends[0];
        }
    }

    public Person[] findSelectablePersons(String email_initiator) {
        ArrayList<Person> selectable = new ArrayList<>();
        ArrayList<Person> alreadyFriendsList = new ArrayList<>();

        for (Person p: this.allPersons) {
            for(Friends f : this.alreadyFriends) {
                if(f.getEmail_p_friend().equals(p.getEmail())) {
                    alreadyFriendsList.add(p);
                }
            }
        }

        for (Person p: this.allPersons) {
            if(!alreadyFriendsList.contains(p) && !p.getEmail().equals(email_initiator)) {
                selectable.add(p);
            }
        }

        Person[] selectables = new Person[selectable.size()];
        selectables = selectable.toArray(selectables);

        return selectables;
    }
}
