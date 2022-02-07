package aau.at.friendsifyfrontendservice.services;

import aau.at.friendsifyfrontendservice.annotations.Generated;
import aau.at.friendsifyfrontendservice.models.WeatherResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Generated
public class WeatherService {

    private final RestTemplate restTemplate;

    @Value("${endpoint.weather}")
    private String weatherServiceEndpoint;

    public WeatherService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public WeatherResult getWeatherByLocation(String location) {
        return restTemplate.getForObject(weatherServiceEndpoint + "/" + location, WeatherResult.class);
    }
}
