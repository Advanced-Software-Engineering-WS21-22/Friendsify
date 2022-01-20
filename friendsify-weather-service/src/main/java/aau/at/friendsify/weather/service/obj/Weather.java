package aau.at.friendsify.weather.service.obj;

import aau.at.friendsify.weather.service.service.WeatherClient;
import lombok.Data;

@Data
public class Weather {
    private String main;
    private String description;
    private String icon;

    public String getIconUrl() {
        return String.format(WeatherClient.baseUrlIcons, icon);
    }
}
