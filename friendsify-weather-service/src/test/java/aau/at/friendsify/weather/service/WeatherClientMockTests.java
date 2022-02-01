package aau.at.friendsify.weather.service;

import aau.at.friendsify.weather.service.exception.CityNotFoundException;
import aau.at.friendsify.weather.service.obj.WeatherResult;
import aau.at.friendsify.weather.service.service.WeatherClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherClientMockTests implements ITestValues {

    @Mock
    private WeatherClient weatherClient;

    @Test
    void testValidCity() {
        WeatherResult result = new WeatherResult();
        when(weatherClient.byCityName(CITY)).thenReturn(result);

        assertNotNull(weatherClient.byCityName(CITY));
    }

    @Test
    void testInvalidCity() {
        when(weatherClient.byCityName(INVALID_CITY)).thenThrow(new CityNotFoundException(INVALID_CITY));

        assertThrows(CityNotFoundException.class, () -> {
            weatherClient.byCityName(INVALID_CITY);
        });
    }
}
