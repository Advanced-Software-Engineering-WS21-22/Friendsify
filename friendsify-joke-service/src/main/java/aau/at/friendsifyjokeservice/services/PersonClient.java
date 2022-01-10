package aau.at.friendsifyjokeservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("email.host")
    private String host;

    public PersonClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(host).build();
    }

    public String emailOfPerson(Long id) {
        return "test@domain.com";
    }
}