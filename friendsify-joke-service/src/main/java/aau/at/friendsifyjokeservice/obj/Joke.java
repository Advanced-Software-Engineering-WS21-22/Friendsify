package aau.at.friendsifyjokeservice.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Joke{
    private String description;
    private String language;
    private String background;
    private String category;
    private String date;
    @JsonProperty("joke")
    private JokeContent jokeContent;
}