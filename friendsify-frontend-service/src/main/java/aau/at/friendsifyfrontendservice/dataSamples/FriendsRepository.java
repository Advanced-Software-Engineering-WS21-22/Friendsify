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
        Friends fs1 = new Friends(Long.valueOf(0), Long.valueOf(3), Long.valueOf(4), LocalDate.of(2019,1,31), false);
        Friends fs2 = new Friends(Long.valueOf(1), Long.valueOf(1), Long.valueOf(0), LocalDate.of(2020,6,1), false);
        Friends fs3 = new Friends(Long.valueOf(2), Long.valueOf(6), Long.valueOf(5), LocalDate.of(2021,2,18), false);
        Friends fs4 = new Friends(Long.valueOf(3), Long.valueOf(4), Long.valueOf(5), LocalDate.of(2020,8,21), true);
        Friends fs5 = new Friends(Long.valueOf(4), Long.valueOf(0), Long.valueOf(2), LocalDate.of(2020,8,21), true);
        Friends fs6 = new Friends(Long.valueOf(5), Long.valueOf(0), Long.valueOf(5), LocalDate.of(2021,9,16), true);

        this.friends.add(fs1);
        this.friends.add(fs2);
        this.friends.add(fs3);
        this.friends.add(fs4);
        this.friends.add(fs5);
        this.friends.add(fs6);
    }

    public ArrayList<Friends> getFriendsActive(Long id_initiator) {
        ArrayList<Friends> friends_active = new ArrayList<>();
        for (Friends fs: this.friends) {
            if(fs.getId_initiator() == id_initiator) {
                friends_active.add(fs);
            }
        }

        return friends_active;
    }

    public ArrayList<Friends> getFriendsPassive(Long id_receiver) {
        ArrayList<Friends> friends_passive = new ArrayList<>();
        for (Friends fs: this.friends) {
            if(fs.getId_receiver() == id_receiver) {
                friends_passive.add(fs);
            }
        }
        return friends_passive;
    }


}
