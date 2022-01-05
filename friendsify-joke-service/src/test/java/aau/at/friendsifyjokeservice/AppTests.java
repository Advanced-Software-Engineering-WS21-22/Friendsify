package aau.at.friendsifyjokeservice;

import aau.at.friendsifyjokeservice.services.JokeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AppTests {

    private JokeService service;

    @BeforeEach
    public void setup() {
        this.service = new JokeService(WebClient.builder());
    }

    @Test
    public void testJokeOfTheDay() {
        String joke = this.service.jokeOfTheDay();

        assertNotNull(joke);
        assertNotEquals("", joke);
    }

    @Test
    public void testRandomJoke() {
        String joke = this.service.randomJoke();

        assertNotNull(joke);
        assertNotEquals("", joke);
    }

    @Test
    public void testJokeTypes() {
        String joke = this.service.getJokebyType("XYZ");

        assertNotNull(joke);
        assertNotEquals("", joke);
    }

}
