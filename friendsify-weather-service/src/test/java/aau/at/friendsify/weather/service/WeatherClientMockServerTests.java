package aau.at.friendsify.weather.service;

import aau.at.friendsify.weather.service.exception.CityNotFoundException;
import aau.at.friendsify.weather.service.obj.WeatherResult;
import aau.at.friendsify.weather.service.service.WeatherClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherClientMockServerTests implements ITestValues {

    private static MockWebServer backend;
    private WeatherClient client;

    @BeforeAll
    public static void startUp() throws IOException {
        backend = new MockWebServer();
        backend.start();
    }

    @AfterAll
    public static void shutdown() throws IOException {
        backend.shutdown();
    }

    @BeforeEach
    public void setUp() throws IOException {
        this.client = new WeatherClient("http://localhost:" + backend.getPort());
    }

    @Test
    void testValidCity() {
        String response
                = "{\"coord\":{\"lon\":13.8558,\"lat\":46.6103},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"base\":\"stations\",\"main\":{\"temp\":2.88,\"feels_like\":2.88,\"temp_min\":-3.33,\"temp_max\":6.62,\"pressure\":1029,\"humidity\":68,\"sea_level\":1029,\"grnd_level\":969},\"visibility\":10000,\"wind\":{\"speed\":0.5,\"deg\":22,\"gust\":0.76},\"clouds\":{\"all\":5},\"dt\":1643186897,\"sys\":{\"type\":2,\"id\":2011437,\"country\":\"AT\",\"sunrise\":1643178936,\"sunset\":1643212698},\"timezone\":3600,\"id\":2762372,\"name\":\"Villach\",\"cod\":200}";
        backend.enqueue(new MockResponse().setBody(response).setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        WeatherResult result = client.byCityName(CITY);

        assertNotNull(result);
        assertEquals(2.88, result.getTemperature());
        assertEquals("clear sky", result.getDescription());
    }

    @Test
    void testValidWithLangCity() {
        String response
                = "{\"coord\":{\"lon\":13.8558,\"lat\":46.6103},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"base\":\"stations\",\"main\":{\"temp\":2.88,\"feels_like\":2.88,\"temp_min\":-3.33,\"temp_max\":6.62,\"pressure\":1029,\"humidity\":68,\"sea_level\":1029,\"grnd_level\":969},\"visibility\":10000,\"wind\":{\"speed\":0.5,\"deg\":22,\"gust\":0.76},\"clouds\":{\"all\":5},\"dt\":1643186897,\"sys\":{\"type\":2,\"id\":2011437,\"country\":\"AT\",\"sunrise\":1643178936,\"sunset\":1643212698},\"timezone\":3600,\"id\":2762372,\"name\":\"Villach\",\"cod\":200}";
        backend.enqueue(new MockResponse().setBody(response).setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        WeatherResult result = client.byCityName(CITY, "en");

        assertNotNull(result);
        assertEquals(2.88, result.getTemperature());
        assertEquals(6.62, result.getTemperatureMax());
        assertEquals(-3.33, result.getTemperatureMin());
        assertEquals(68, result.getHumidity());
        assertEquals(1029, result.getPressure());
        assertEquals(0.5, result.getWindSpeed());
        assertEquals(String.format(WeatherClient.BASE_URL_ICONS, "01d"), result.getIconUrl());
        assertEquals("clear sky", result.getDescription());
    }

    @Test
    void testInValidCity() {
        String response
                = "{\"cod\":\"404\",\"message\":\"city not found\"}";
        backend.enqueue(new MockResponse().setBody(response).setResponseCode(404).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        assertThrows(CityNotFoundException.class, () -> {
            WeatherResult result = client.byCityName(INVALID_CITY);
        });
    }
}
