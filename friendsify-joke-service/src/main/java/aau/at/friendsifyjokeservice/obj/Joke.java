package aau.at.friendsifyjokeservice.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Joke{
    @JsonProperty("joke")
    private JokeContent jokeContent;
}