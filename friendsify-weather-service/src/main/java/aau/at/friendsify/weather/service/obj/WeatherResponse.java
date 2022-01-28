package aau.at.friendsify.weather.service.obj;

import lombok.Getter;

import java.util.List;

@Getter
public class WeatherResponse {
    private List<Weather> weather;
    private WeatherData main;
    private WindData wind;
}
