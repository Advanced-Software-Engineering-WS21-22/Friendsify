package aau.at.friendsifyjokeservice.services;

import aau.at.friendsifyjokeservice.exception.PersonNotFoundException;
import aau.at.friendsifyjokeservice.obj.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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

    public String emailOfPerson(Long id) throws PersonNotFoundException {
        Person p = this.getPerson(id);

        return p.getEmail();
    }

    private Person getPerson(Long id) throws PersonNotFoundException {
        try {
            return this.rt.getForObject(host + "?id={id}", Person.class, id);
        } catch (HttpClientErrorException exception) {
            log.error("client error", exception);
            throw new PersonNotFoundException();
        }
    }
}