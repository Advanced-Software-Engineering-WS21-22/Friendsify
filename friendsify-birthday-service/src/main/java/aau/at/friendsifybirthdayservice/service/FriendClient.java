package aau.at.friendsifybirthdayservice.service;

import aau.at.friendsifybirthdayservice.obj.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class FriendClient {

    private final WebClient webClient;

    public FriendClient(WebClient.Builder webClientBuilder) {
        String host = System.getProperty("friend.host");
        this.webClient = webClientBuilder.baseUrl(host).build();
    }


    public List<Person> getFriendOfPerson(Person birthdayKid) {

        return Arrays.asList(new Person());
    }
}
