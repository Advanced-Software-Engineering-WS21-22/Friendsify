package aau.at.friendsifyfrontendservice.services;

import aau.at.friendsifyfrontendservice.models.Recommendation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecommendationService {

    private final RestTemplate restTemplate;

    @Value("${endpoint.recommendations}")
    private String recommendationServiceEndpoint;

    public RecommendationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Recommendation getRecommendationForPerson(Long id_p) {
        Recommendation recommendation = this.restTemplate.getForObject(this.recommendationServiceEndpoint + "/" + id_p, Recommendation.class);
        return recommendation;
    }
}
