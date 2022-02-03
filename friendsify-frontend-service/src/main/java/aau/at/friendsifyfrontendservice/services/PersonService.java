package aau.at.friendsifyfrontendservice.services;

import aau.at.friendsifyfrontendservice.models.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
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
        return restTemplate.getForObject(personServiceEndpoint, Person[].class);
    }

    public Person getPersonByEMail(String email) {
        return restTemplate.getForObject(personServiceEndpoint + "?email=" + email, Person.class);
    }

    public Person getPersonById(Long id) {
        return restTemplate.getForObject(personServiceEndpoint + "?id=" + id, Person.class);
    }

    public void addPerson(Person person) throws HttpServerErrorException {
        restTemplate.postForObject(personServiceEndpoint, person, Person.class);
    }



}
