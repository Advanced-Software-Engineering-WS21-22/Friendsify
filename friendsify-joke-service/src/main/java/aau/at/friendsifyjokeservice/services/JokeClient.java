package aau.at.friendsifyjokeservice.services;

import aau.at.friendsifyjokeservice.enums.JokeTypes;
import aau.at.friendsifyjokeservice.obj.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class JokeClient {

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private PersonClient personClient;

    private final WebClient jokesOneClient;
    private final WebClient jokeApiClient;

    @Autowired
    public JokeClient(
            @Value("${joke.one.url}") String jokesOneUrl,
            @Value("${joke.api.url}") String jokeApiUrl,
            @Value("${joke.api.token}") String token
    ) {
        this.jokesOneClient = WebClient.builder().baseUrl(jokesOneUrl).build();
        this.jokeApiClient = WebClient.builder()
                .baseUrl(jokeApiUrl)
                .defaultHeader("x-rapidapi-host", "jokeapi-v2.p.rapidapi.com")
                .defaultHeader("x-rapidapi-key", token)
                .build();
    }

    public String jokeOfTheDay() {
        JokeResponse resp = this.jokesOneClient
                .get()
                .uri("/jod")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(JokeResponse.class)
                .block();

        return Optional.ofNullable(resp)
                .map(JokeResponse::getContents)
                .map(Contents::getJokes)
                .flatMap(j -> j.stream().limit(1).findFirst())
                .map(Joke::getJokeContent)
                .map(JokeContent::getText)
                .orElse(null);
    }

    public String randomJoke() {
        return Optional.ofNullable(
                this.jokeApiClient
                        .get()
                        .retrieve()
                        .bodyToMono(JokeApiContent.class)
                        .block()
        ).map(JokeApiContent::getJoke).orElse(null);
    }

    public String getJokebyType(String type) {
        JokeTypes jt = JokeTypes.find(type);

        return (jt == JokeTypes.JOD) ? jokeOfTheDay() : randomJoke();
    }

    public String tellYourFriendAJoke(Long personId, Long friendId) {

        // call Person service
        String from = personClient.emailOfPerson(personId);
        String to = personClient.emailOfPerson(friendId);

        // call joke service
        String joke = this.randomJoke();

        // call Email Service
        return emailClient.send(new Email(from, to, "Joke from your friend", joke));
    }
}