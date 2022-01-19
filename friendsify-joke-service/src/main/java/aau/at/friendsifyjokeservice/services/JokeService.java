package aau.at.friendsifyjokeservice.services;

import aau.at.friendsifyjokeservice.enums.JokeTypes;
import aau.at.friendsifyjokeservice.obj.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class JokeService {
    private final WebClient jokesOneClient;
    private final WebClient jokeApiClient;

    @Value("#{environment.JOKE_API_TOKEN}")
    private String token;

    public JokeService(WebClient.Builder webClientBuilder) {
        this.jokesOneClient = webClientBuilder.baseUrl("https://api.jokes.one").build();
        this.jokeApiClient = webClientBuilder
                .baseUrl("https://jokeapi-v2.p.rapidapi.com/joke/Programming?type=single&format=json&blacklistFlags=nsfw,racist&safe-mode=true")
                .defaultHeader("x-rapidapi-host", "jokeapi-v2.p.rapidapi.com")
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
                .map(Joke::getJoke)
                .map(JokeContent::getText)
                .orElse(null);
    }

    public String randomJoke() {
        System.out.println(token);
        return Optional.ofNullable(
                this.jokeApiClient
                        .get()
                        .header("x-rapidapi-key", token)
                        .retrieve()
                        .bodyToMono(JokeApiContent.class)
                        .block()
        ).map(JokeApiContent::getJoke).orElse(null);
    }

    public String getJokebyType(String type) {
        System.out.println(type);
        JokeTypes jt = JokeTypes.find(type);
        System.out.println(jt.name());
        String joke = null;
        switch (jt) {
            case JOD:
                joke = jokeOfTheDay();
                break;
            case RANDOM:
                joke = randomJoke();
                break;
        }
        return joke;
    }
}