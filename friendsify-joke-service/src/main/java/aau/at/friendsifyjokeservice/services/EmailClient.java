package aau.at.friendsifyjokeservice.services;

import aau.at.friendsifyjokeservice.exception.PersonNotFoundException;
import aau.at.friendsifyjokeservice.obj.Email;
import aau.at.friendsifyjokeservice.obj.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EmailClient  {

    private final RestTemplate rt;

    @Value("${email.host.url}")
    private String host;

    public EmailClient() {
        this.rt = new RestTemplateBuilder().build();
    }

    public EmailClient(String host) {
        this();
        this.host = host;
    }

    public String send(Email email) {
        return this.rt.postForObject(host, email, String.class);
    }
}