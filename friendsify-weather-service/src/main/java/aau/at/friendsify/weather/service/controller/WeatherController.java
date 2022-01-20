package aau.at.friendsify.weather.service.controller;

import aau.at.friendsify.weather.service.obj.WeatherResult;
import aau.at.friendsify.weather.service.service.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherClient weatherClient;

    @GetMapping("/{cityName}")
    public WeatherResult getWeather(@PathVariable("cityName") String cityName) {
        return weatherClient.byCityName(cityName);
    }

    @GetMapping("/{cityName}/{lang}")
    public WeatherResult getWeather(@PathVariable("cityName") String cityName, @PathVariable("lang") String lang) {
        return weatherClient.byCityName(cityName, lang);
    }

}
