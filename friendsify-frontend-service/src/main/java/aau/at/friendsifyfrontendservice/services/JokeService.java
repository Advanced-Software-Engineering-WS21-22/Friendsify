package aau.at.friendsifyfrontendservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokeService {

    private final RestTemplate restTemplate;

    @Value("${endpoint.joke}")
    private String jokeServiceEndpoint;

    public JokeService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getJoke() {
        String joke = this.restTemplate.getForObject(jokeServiceEndpoint, String.class);
        return joke;
    }
}
