package aau.at.friendsifyfrontendservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class FriendsService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${endpoint.friends}")
    private String friendsServiceEndpoint;

    public FriendsService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = new ObjectMapper();
    }
}
