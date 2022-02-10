package aau.at.friendsifyrecommendationsservice.services;

import aau.at.friendsifyrecommendationsservice.models.Friends;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FriendsService {

    private final RestTemplate restTemplate;

    @Value("${endpoint.friends}")
    private String friendsServiceEndpoint;

    public FriendsService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Friends[] getFriends() {
        Friends[] friends = restTemplate.getForObject(friendsServiceEndpoint, Friends[].class);
        return friends;
    }

}
