package aau.at.friendsifyjokeservice;

import aau.at.friendsifyjokeservice.services.JokeClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JokeClientTests {

    private static MockWebServer backend;
    private JokeClient jokeClient;

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
        String url = "http://localhost:" + backend.getPort();
        this.jokeClient = new JokeClient(url, url);
    }

    @Test
    void testJokeOfTheDay() {
        String joke = "Q. Why shouldn't you marry a tennis player? A. Because Love means nothing to them.";
        String response = "{\"success\":{\"total\":1},\"contents\":{\"jokes\":[{\"category\":\"jod\",\"title\":\"Joke of the day \",\"description\":\"Joke of the day \",\"background\":\"\",\"date\":\"2019-01-23\",\"joke\":{\"title\":\"Courtship Signals\",\"length\":\"83\",\"clean\":\"1\",\"racial\":\"0\",\"date\":\"2019-01-23\",\"id\":\"He3_WpaNfBV1Hs7zMLsR4QeF\",\"text\":\"" +joke+ "\"}}],\"copyright\":\"2018-20 https://jokes.one\"}}";
        backend.enqueue(new MockResponse().setBody(response).setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        String result = this.jokeClient.jokeOfTheDay();

        assertNotNull(result);
        assertEquals(joke, result);
    }

    @Test
    void testRandomJoke() {
        String joke = "How do you tell HTML from HTML5? - Try it out in Internet Explorer - Did it work? - No? - It's HTML5.";
        String response = "{\"error\":false,\"category\":\"Programming\",\"type\":\"single\",\"joke\":\"" + joke +"\",\"flags\":{\"nsfw\":false,\"religious\":false,\"political\":false,\"racist\":false,\"sexist\":false,\"explicit\":false},\"id\":43,\"safe\":true,\"lang\":\"en\"}";
        backend.enqueue(new MockResponse().setBody(response).setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        String result = this.jokeClient.randomJoke();

        assertNotNull(result);
        assertEquals(joke, result);
    }
}
