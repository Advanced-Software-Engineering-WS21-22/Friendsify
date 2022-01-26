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
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonClient {

    private final RestTemplate rt;

    @Value("${person.host.url}")
    private String host;


    public PersonClient() {
        this.rt = new RestTemplateBuilder().build();
    }
    public PersonClient(String host) {
        this();
        this.host = host;
    }


    public Person getPerson(Long id) throws ResourceNotFoundException {
        return getPerson("id", id);
    }

    public Person getPerson(String email) throws ResourceNotFoundException {
        return getPerson("email", email);
    }

    private Person getPerson(String filter, Object value) {
        try {
            String queryParam = String.format("?%s={%s}", filter, filter);
            return this.rt.getForObject(host + queryParam, Person.class, value);
        } catch (HttpClientErrorException exception) {
            log.error("client error", exception);
            throw new ResourceNotFoundException();
        }
    }

    public List<Person> getPersons() {
        return Arrays.asList(Objects.requireNonNull(this.rt.getForObject(host, Person[].class)));
    }

    public List<Person> getPersonsForFriendList(List<Friend> friendList) {
        return friendList.stream().map(f -> this.getPerson(f.getEmailFriend())).collect(Collectors.toList());
    }



}
