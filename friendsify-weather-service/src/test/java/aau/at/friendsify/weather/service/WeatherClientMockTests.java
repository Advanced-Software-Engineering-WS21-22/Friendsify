package aau.at.friendsify.weather.service;

import aau.at.friendsify.weather.service.exception.CityNotFoundException;
import aau.at.friendsify.weather.service.obj.WeatherResult;
import aau.at.friendsify.weather.service.service.WeatherClient;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WeatherClientMockTests implements ITestValues {

    @Mock
    private WeatherClient weatherClient;

    @Test
    public void testValidCity() {
        WeatherResult result = new WeatherResult();
        when(weatherClient.byCityName(CITY)).thenReturn(result);

        assertNotNull(weatherClient.byCityName(CITY));
    }

    @Test
    public void testInvalidCity() {
        when(weatherClient.byCityName(INVALID_CITY)).thenThrow(new CityNotFoundException(INVALID_CITY));

        assertThrows(CityNotFoundException.class, () -> {
            weatherClient.byCityName(INVALID_CITY);
        });
    }
}
