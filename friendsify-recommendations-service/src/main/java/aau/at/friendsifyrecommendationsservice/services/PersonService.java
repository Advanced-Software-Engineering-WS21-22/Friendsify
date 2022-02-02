package aau.at.friendsifyrecommendationsservice.services;

import aau.at.friendsifyrecommendationsservice.models.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonService {

    private final RestTemplate restTemplate;

    @Value("${endpoint.person}")
    private String personServiceEndpoint;

    public PersonService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Person[] getPersons() {
        Person[] allPersons = restTemplate.getForObject(personServiceEndpoint, Person[].class);
        return allPersons;
    }

}
