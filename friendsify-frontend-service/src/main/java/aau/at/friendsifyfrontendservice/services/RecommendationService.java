package aau.at.friendsifyfrontendservice.services;

import aau.at.friendsifyfrontendservice.annotations.Generated;
import aau.at.friendsifyfrontendservice.models.Recommendation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Generated
public class RecommendationService {

    private final RestTemplate restTemplate;

    @Value("${endpoint.recommendations}")
    private String recommendationServiceEndpoint;

    public RecommendationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Recommendation getRecommendationForPerson(Long id_p) {
        return this.restTemplate.getForObject(this.recommendationServiceEndpoint + "/" + id_p, Recommendation.class);
    }
}
