package aau.at.friendsifyfrontendservice.services;

import aau.at.friendsifyfrontendservice.models.WeatherResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    @Value("${endpoint.weather}")
    private String weatherServiceEndpoint;

    public WeatherService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public WeatherResult getWeatherByLocation(String location) {
        WeatherResult weatherResult = null;
        weatherResult = restTemplate.getForObject(weatherServiceEndpoint + "/" + location, WeatherResult.class);
        return weatherResult;
    }

    public String weatherSummary(WeatherResult weatherResult) {
        String summary = "The current temperature is " + weatherResult.getTemperature() +
                "°C. It can reach a max of " + weatherResult.getTemperatureMax() +
                "°C and a min of " + weatherResult.getTemperatureMin() +
                "°C during the day. \n" +
                "Humidity: " + weatherResult.getHumidity() + "\n" +
                "Pressure: " + weatherResult.getPressure() + "\n" +
                "WindSpeed: " + weatherResult.getWindSpeed() + "\n";
        return summary;
    }
}
