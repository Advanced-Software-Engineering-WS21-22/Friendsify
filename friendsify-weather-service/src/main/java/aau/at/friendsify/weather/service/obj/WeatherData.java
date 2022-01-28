package aau.at.friendsify.weather.service.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WeatherData {
    @JsonProperty("temp")
    private double temperature;
    @JsonProperty("temp_min")
    private double temperatureMin;
    @JsonProperty("temp_max")
    private double temperatureMax;
    private double pressure;
    private double humidity;
}
