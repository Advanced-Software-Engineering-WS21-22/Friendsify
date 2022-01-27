package aau.at.friendsify.weather.service.obj;

import aau.at.friendsify.weather.service.service.WeatherClient;
import lombok.Getter;

@Getter
public class Weather {
    private String main;
    private String description;
    private String icon;

    public String getIconUrl() {
        return String.format(WeatherClient.BASE_URL_ICONS, icon);
    }
}
