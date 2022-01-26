package aau.at.friendsify.weather.service.obj;

import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {
    private Coordinates coord;
    private List<Weather> weather;
    private String base;
    private WeatherData main;
    private int visibility;
    private WindData wind;
    private CloudData clouds;
    private String name;
}
