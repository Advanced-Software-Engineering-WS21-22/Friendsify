package aau.at.friendsifyfrontendservice.services;

import aau.at.friendsifyfrontendservice.models.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${endpoint.person}")
    private String personServiceEndpoint;

    public PersonService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = new ObjectMapper();
    }

    public Person[] getPersons() {
        System.out.println("DEBUG: " + this.personServiceEndpoint);
        Person[] allPersons = null;
        allPersons = restTemplate.getForObject(personServiceEndpoint, Person[].class);
        System.out.println("All Persons from Service: " + allPersons);
        return allPersons;
    }



}