package aau.at.friendsify.weather.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CityNotFoundException extends Exception {

    public CityNotFoundException(String cityName) {
        super(String.format("City %s not found", cityName));
    }
}
