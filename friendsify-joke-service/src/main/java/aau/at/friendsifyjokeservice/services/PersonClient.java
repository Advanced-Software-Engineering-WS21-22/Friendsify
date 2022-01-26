package aau.at.friendsifyjokeservice.services;

import aau.at.friendsifyjokeservice.exception.PersonNotFoundException;
import aau.at.friendsifyjokeservice.obj.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PersonClient {

    private final WebClient webClient;

    @Value("${person.host}")
    private String host;

    public PersonClient() {
        this.webClient = WebClient.builder().build();
    }

    public String emailOfPerson(Long id) throws PersonNotFoundException {
        Person p = this.getPerson(id);

        return p.getEmail();
    }

    private Person getPerson(Long id) throws PersonNotFoundException {
        return this.webClient
                .get()
                .uri(host, uriBuilder -> uriBuilder.queryParam("id", id).build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    throw new PersonNotFoundException();
                })
                .bodyToMono(Person.class)
                .block();
    }
}