package aau.at.friendsify.weather.service.service;

import aau.at.friendsify.weather.service.exception.CityNotFoundException;
import aau.at.friendsify.weather.service.obj.WeatherResponse;
import aau.at.friendsify.weather.service.obj.WeatherResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class WeatherClient {

    private final WebClient webClient;

    public static String baseUrlIcons = "http://openweathermap.org/img/wn/%s@2x.png";

    @Value("#{environment.WEATHER_API_TOKEN}")
    private String token;

    public WeatherClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openweathermap.org/data/2.5/weather").build();
    }

    public WeatherResult byCityName(String cityName) {
        return byCityName(cityName, null);
    }

    public WeatherResult byCityName(String cityName, String language) {
        String lang = "en";
        if (StringUtils.isNotBlank(language)) {
            lang = language;
        }

        String finalLang = lang;
        WeatherResponse resp = this.webClient
                .get()
                .uri(builder -> builder.queryParam("appid", token)
                        .queryParam("units", "metric")
                        .queryParam("lang", finalLang)
                        .queryParam("q", cityName)
                .build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    return Mono.error(new CityNotFoundException(cityName));
                })
                .bodyToMono(WeatherResponse.class)
                .block();

        return new WeatherResult(resp);
    }

}