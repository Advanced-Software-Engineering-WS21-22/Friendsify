package aau.at.friendsifyfrontendservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResult {

    private double temperature;
    private double temperatureMin;
    private double temperatureMax;
    private double humidity;
    private double pressure;
    private double windSpeed;
    private String description;
    private String iconUrl;

    public WeatherResult(double temperature, double temperatureMin, double temperatureMax, double humidity, double pressure, double windSpeed, String description, String iconUrl) {
        this.temperature = temperature;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.description = description;
        this.iconUrl = iconUrl;
    }

    public WeatherResult() {

    }
}
