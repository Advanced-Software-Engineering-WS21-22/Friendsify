package aau.at.anniversaryreminder.service;

import aau.at.anniversaryreminder.exception.ResourceNotFoundException;
import aau.at.anniversaryreminder.obj.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {

    private final RestTemplate rt;

    @Value("${person.host.url}")
    private String host;

    public PersonServiceImpl() {
        this.rt = new RestTemplateBuilder().build();
    }

    public PersonServiceImpl(String host, RestTemplate rt) {
        this.host = host;
        this.rt = rt;
    }

    public PersonServiceImpl(String host) {
        this();
        this.host = host;
    }

    @Override
    public String getNameByEmail(String email) {
        String filter = "?email="+email;
        Person[] persons;
        try {
            persons = this.rt.getForObject(host + filter, Person[].class);
        } catch (HttpClientErrorException e) {
            throw new ResourceNotFoundException();
        }
        return Objects.requireNonNull(persons)[0].getFullName();
    }
}
