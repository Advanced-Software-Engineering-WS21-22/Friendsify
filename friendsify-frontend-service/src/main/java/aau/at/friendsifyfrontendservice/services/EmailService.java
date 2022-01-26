package aau.at.friendsifyfrontendservice.services;

import aau.at.friendsifyfrontendservice.models.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailService {

    private final RestTemplate restTemplate;

    @Value("${endpoint.email}")
    private String emailServiceEndpoint;

    public EmailService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void sendEmail(Email email) {
        restTemplate.postForObject(emailServiceEndpoint, email, Email.class);
    }
}
