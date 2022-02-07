package aau.at.anniversaryreminder.service;

import aau.at.anniversaryreminder.obj.Friend;

import java.time.LocalDate;
import java.util.List;

public interface FriendsService {

    List<Friend> getFriendsOf(String emailInitiator);

    boolean areTheyFriends(String emailInitiator, String emailPossibleFriend);

    LocalDate getFriendshipStartDate(String emailInitiator, String emailFriend);

}
