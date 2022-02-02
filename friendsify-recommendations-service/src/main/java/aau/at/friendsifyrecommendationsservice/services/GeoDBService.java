package aau.at.friendsifyrecommendationsservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

public class GeoDBService {

    private final WebClient geoDBClient;

    @Value("${geoDB.api.url}")
    private String geoDBUrl;

    @Value("${geoDB.api.token}")
    private String geoDBToken;

    public GeoDBService() {
        this.geoDBClient = WebClient.builder()
                .baseUrl(geoDBUrl)
                .defaultHeader("x-rapidapi-host", "wft-geo-db.p.rapidapi.com")
                .defaultHeader("x-rapidapi-key", geoDBToken)
                .build();
    }
}
