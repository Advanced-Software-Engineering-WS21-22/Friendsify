package aau.at.friendsifyfrontendservice.dataSamples;

import aau.at.friendsifyfrontendservice.models.Friends;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class FriendsRepository {

    private static FriendsRepository friendsRepository = null;

    private ArrayList<Friends> friends;

    private FriendsRepository() {
        this.friends = new ArrayList<>();
        this.fillWithSamples();
    }

    public static FriendsRepository getFriendsRepository() {
        if(friendsRepository == null) {
            friendsRepository = new FriendsRepository();
        }

        return friendsRepository;
    }

    private void fillWithSamples() {
        Friends fs1 = new Friends(Long.valueOf(0), "hans.m@gmail.com", "max@mustermann.de", LocalDate.of(2019,1,31), false);
        Friends fs2 = new Friends(Long.valueOf(1), "anna@mustermann.de", "hans.m@gmail.com", LocalDate.of(2020,6,1), false);
        Friends fs3 = new Friends(Long.valueOf(2), "max@mustermann.de", "john.doe@email.com", LocalDate.of(2021,2,18), false);
        Friends fs4 = new Friends(Long.valueOf(3), "hans.m@gmail.com", "anna@mustermann.de", LocalDate.of(2020,8,21), false);
        Friends fs5 = new Friends(Long.valueOf(4), "max@mustermann.de", "john.doe@email.com", LocalDate.of(2020,8,21), false);
        Friends fs6 = new Friends(Long.valueOf(5), "max@mustermann.de", "anna@mustermann.de", LocalDate.of(2021,9,16), false);

        this.friends.add(fs1);
        this.friends.add(fs2);
        this.friends.add(fs3);
        this.friends.add(fs4);
        this.friends.add(fs5);
        this.friends.add(fs6);
    }

    public ArrayList<Friends> getFriendsActive(String email_p_initiator) {
        ArrayList<Friends> friends_active = new ArrayList<>();
        for (Friends fs: this.friends) {
            if(fs.getEmail_p_initiator().equals(email_p_initiator)) {
                friends_active.add(fs);
            }
        }

        return friends_active;
    }

    public ArrayList<Friends> getFriendsPassive(String email_p_friend) {
        ArrayList<Friends> friends_passive = new ArrayList<>();
        for (Friends fs: this.friends) {
            if(fs.getEmail_p_friend().equals(email_p_friend)) {
                friends_passive.add(fs);
            }
        }
        return friends_passive;
    }


}
