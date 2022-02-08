package aau.at.friendsifyfrontendservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AnniversaryService {

    private final RestTemplate restTemplate;

    @Value("${endpoint.anniversary}")
    private String anniversaryEndpoint;

    public AnniversaryService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getAnniversary(String email_initiator, String email_friend) {
        return this.restTemplate.getForObject(anniversaryEndpoint+"/reminder?email_initiator="+email_initiator+"&email_friend="+email_friend, String.class);
    }
}
