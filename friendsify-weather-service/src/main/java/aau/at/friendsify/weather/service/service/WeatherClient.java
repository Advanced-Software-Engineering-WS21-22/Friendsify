package aau.at.friendsify.weather.service.service;

import aau.at.friendsify.weather.service.exception.CityNotFoundException;
import aau.at.friendsify.weather.service.obj.WeatherResponse;
import aau.at.friendsify.weather.service.obj.WeatherResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherClient {

    private final WebClient webClient;

    public static final String BASE_URL_ICONS = "http://openweathermap.org/img/wn/%s@2x.png";

    @Value("#{environment.WEATHER_API_TOKEN}")
    private String token;

    @Autowired
    public WeatherClient(@Value("${weather.api.url}") String url) {
        this.webClient = WebClient.builder().baseUrl(url).build();
    }

    public WeatherResult byCityName(String cityName) throws CityNotFoundException {
        return byCityName(cityName, null);
    }

    public WeatherResult byCityName(String cityName, String language) throws CityNotFoundException {
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
                    throw new CityNotFoundException(cityName);
                })
                .bodyToMono(WeatherResponse.class)
                .block();

        return new WeatherResult(resp);
    }

}