package aau.at.friendsify.weather.service.obj;

import lombok.Data;

@Data
public class WindData {
    private double speed;
    private double deg;
    private double gust;
}
