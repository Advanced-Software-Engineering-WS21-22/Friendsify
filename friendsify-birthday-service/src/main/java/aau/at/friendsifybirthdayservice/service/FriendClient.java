package aau.at.friendsifybirthdayservice.service;

import aau.at.friendsifybirthdayservice.exception.ResourceNotFoundException;
import aau.at.friendsifybirthdayservice.obj.Friend;
import aau.at.friendsifybirthdayservice.obj.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FriendClient {


    private final RestTemplate rt;

    @Value("${friend.host.url}")
    private String host;

    public FriendClient() {
        this.rt = new RestTemplateBuilder().build();
    }
    public FriendClient(String host) {
        this();
        this.host = host;
    }

    public List<Friend> getFriendsOfPerson(Person person) {
        return getFriendsWithFilter(person, "email_initiator");
    }

    public List<Friend> getPersonsThatAreFriendOf(Person person) {
        return getFriendsWithFilter(person, "email_friend");
    }

    private List<Friend> getFriendsWithFilter(Person person, String property) throws ResourceNotFoundException {
        String filter = String.format("?%s={email}", property);
        Friend[] friends;
        try {
            friends = this.rt.getForObject(host + filter, Friend[].class, person.getEmail());
        } catch (HttpClientErrorException e) {
            throw new ResourceNotFoundException();
        }
        return Arrays.asList(friends);
    }
}
