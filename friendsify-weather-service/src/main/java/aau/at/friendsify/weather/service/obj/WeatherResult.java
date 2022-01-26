package aau.at.friendsify.weather.service.obj;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class WeatherResult {
    private Double temperature;
    private Double temperatureMin;
    private Double temperatureMax;
    private Double humidity;
    private Double pressure;
    private Double windSpeed;
    private String description;
    private String iconUrl;

    public WeatherResult(WeatherResponse resp) {
        final Optional<WeatherData> main = Optional.ofNullable(resp.getMain());
        final Optional<Weather> weather = resp.getWeather().stream().findFirst();
        this.temperature = main.map(WeatherData::getTemperature).orElse(null);
        this.iconUrl = weather.map(Weather::getIconUrl).orElse(null);
        this.description = weather.map(Weather::getDescription).orElse(null);
        this.temperatureMin = main.map(WeatherData::getTemperatureMin).orElse(null);
        this.temperatureMax = main.map(WeatherData::getTemperatureMax).orElse(null);;
        this.humidity = main.map(WeatherData::getHumidity).orElse(null);
        this.pressure = main.map(WeatherData::getPressure).orElse(null);
        this.windSpeed = Optional.ofNullable(resp.getWind()).map(WindData::getSpeed).orElse(null);
    }
}
