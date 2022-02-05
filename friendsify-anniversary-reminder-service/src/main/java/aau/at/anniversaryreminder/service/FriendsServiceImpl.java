package aau.at.anniversaryreminder.service;

import aau.at.anniversaryreminder.obj.Friend;
import aau.at.anniversaryreminder.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.swing.plaf.PanelUI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FriendsServiceImpl implements FriendsService {

    private final RestTemplate rt;

    @Value("${friend.host.url}")
    private String host;

    public FriendsServiceImpl(String host, RestTemplate rt) {
        this.host = host;
        this.rt = rt;
    }

    public FriendsServiceImpl() {
        this.rt = new RestTemplateBuilder().build();
    }

    public FriendsServiceImpl(String host) {
        this();
        this.host = host;
    }

    @Override
    public List<Friend> getFriendsOf(String emailInitiator) throws ResourceNotFoundException {
        String filter = "?email_initiator=" + emailInitiator;
        Friend[] friends;
        try {
            friends = this.rt.getForObject(host + filter, Friend[].class);
        } catch (HttpClientErrorException e) {
            throw new ResourceNotFoundException();
        }
        return Arrays.asList(friends);
    }

    @Override
    public boolean areTheyFriends(String emailInitiator, String emailPossibleFriend) throws ResourceNotFoundException {
        String filter = "?email_initiator=" + emailInitiator;
        Stream<Friend> friends;
        try {
            friends = Arrays.stream(this.rt.getForObject(host + filter, Friend[].class)).filter(friendShip -> friendShip.getEmailFriend().equals(emailPossibleFriend));
        } catch (HttpClientErrorException e) {
            throw new ResourceNotFoundException();
        }
        return friends.findAny().isPresent();
    }

    @Override
    public LocalDate getFriendshipStartDate(String emailInitiator, String emailFriend) throws ResourceNotFoundException {
        String filter = "?email_initiator=" + emailInitiator + "&email_friend=" + emailFriend;
        Friend[] friends;
        try {
            friends = Arrays.stream(this.rt.getForObject(host + filter, Friend[].class)).filter(friend -> friend.getEmailFriend().equals(emailFriend)).collect(Collectors.toList()).toArray(Friend[]::new);
        } catch (HttpClientErrorException e) {
            throw new ResourceNotFoundException();
        }
        return Objects.requireNonNull(friends)[0].getFriendshipStartDate();
    }
}
