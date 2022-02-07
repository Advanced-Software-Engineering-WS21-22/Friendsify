package aau.at.friendsifyfrontendservice.services;

import aau.at.friendsifyfrontendservice.annotations.Generated;
import aau.at.friendsifyfrontendservice.models.Friends;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Generated
public class JokeService {

    private final RestTemplate restTemplate;

    @Value("${endpoint.joke}")
    private String jokeServiceEndpoint;

    public JokeService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getJoke() {
        return this.restTemplate.getForObject(jokeServiceEndpoint+"/", String.class);
    }

    public void sendJokeToFriend(Long id_p, Long id_f) {
        this.restTemplate.put(this.jokeServiceEndpoint+"/"+id_p.toString()+"/"+id_f.toString(), null);
    }
}
