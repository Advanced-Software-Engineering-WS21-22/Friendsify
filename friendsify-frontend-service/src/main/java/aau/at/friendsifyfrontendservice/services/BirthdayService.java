package aau.at.friendsifyfrontendservice.services;

import aau.at.friendsifyfrontendservice.models.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class BirthdayService {

    private final RestTemplate restTemplate;

    @Value("${endpoint.birthday}")
    private String birthdayServiceEndpoint;

    public BirthdayService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /*
    public List<Person> getFriendsBirtdays(Long personId) {
        //List<Person> birthdayPersons = this.restTemplate.getForObject(this.birthdayServiceEndpoint + "?personID=" + personId)
    }
    */
}
