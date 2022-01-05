package aau.at.friendsifyjokeservice.services;

import aau.at.friendsifyjokeservice.obj.Contents;
import aau.at.friendsifyjokeservice.obj.Joke;
import aau.at.friendsifyjokeservice.obj.JokeContent;
import aau.at.friendsifyjokeservice.obj.JokeResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class JokeService {
    private final WebClient webClient;

    public JokeService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.jokes.one").build();
    }

    public String jokeOfTheDay() {
        JokeResponse resp = this.webClient
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
}