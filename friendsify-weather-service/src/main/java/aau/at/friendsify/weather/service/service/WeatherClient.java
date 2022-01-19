package aau.at.friendsify.weather.service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherClient {

    private final WebClient webClient;
    @Value("email.host")
    private String host;

    public WeatherClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(host).build();
    }

}