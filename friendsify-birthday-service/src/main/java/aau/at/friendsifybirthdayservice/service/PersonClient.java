package aau.at.friendsifybirthdayservice.service;

import aau.at.friendsifybirthdayservice.obj.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class PersonClient {

    private final WebClient webClient;

    public PersonClient(WebClient.Builder webClientBuilder) {
        String host = System.getProperty("person.host");
        this.webClient = webClientBuilder.baseUrl(host).build();
    }

    public Optional<Person> getPersonById(Long personId) {
        return Optional.ofNullable(new Person());
    }

    public List<Person> getTodaysBirthdayKids() {
        return findByBirthday(LocalDate.now());
    }

    public List<Person> findByBirthday(LocalDate birthday) {
        return Arrays.asList(new Person());
    }
}
